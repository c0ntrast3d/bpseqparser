package it.unicam.pbparser.mappers;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.entities.ParallelComponent;

import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class ParallelComponents {
    public static List<ParallelComponent> generate(List<BPair> input, int primaryLength) {
        System.out.println(String.format("GENERATING PC :: %s", Thread.currentThread().getName()));
        int[] accumulator = new int[primaryLength];
        for (BPair pair : input) {
            final int realIndex = pair.getIndex() - 1;
            final int previous = pair.getIndex() - 2;
            accumulator[realIndex] = pair.getPair();
            accumulator[pair.getPair() - 1] = -1;
            if (realIndex != 0 && pair.getIndex() != primaryLength) {
                if (accumulator[previous] > pair.getPair()) {
                    accumulator[realIndex] = accumulator[previous];
                }
                if (accumulator[pair.getIndex()] < accumulator[realIndex] && accumulator[pair.getIndex()] != 0) {
                    accumulator[pair.getIndex()] = accumulator[realIndex];
                }
            } else {
                if (realIndex == 0) {
                    if (accumulator[pair.getIndex()] < accumulator[realIndex] && accumulator[pair.getIndex()] != 0) {
                        accumulator[pair.getIndex()] = accumulator[realIndex];
                    }
                } else {
                    if (accumulator[previous] > pair.getPair()) {
                        accumulator[realIndex] = accumulator[previous];
                    }
                }
            }
        }
        Stack<ParallelComponent> stack = new Stack<>();
        stack.push(new ParallelComponent(1, accumulator[0], 1));
        for (int counter = 1; counter < accumulator.length; counter++) {
            final ParallelComponent temp = stack.peek();
            if (temp.getPair() == accumulator[counter]) {
                temp.setOccurrence((temp.getOccurrence() + 1));
                stack.pop();
                stack.push(temp);
            } else {
                stack.push(new ParallelComponent(counter + 1, accumulator[counter], 1));
            }
        }

        return stack.stream().filter(item -> item.getPair() != -1).collect(Collectors.toList());
    }
}
