package it.unicam.pbparser;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.entities.ParallelComponent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.List;
import java.util.Stack;

class PCGenerator {
    static List<ParallelComponent> generate(List<BPair> input, int primaryLength) {
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

        Path currentRelativePath = Paths.get("");
        String fileName = currentRelativePath.toAbsolutePath().toString() + "/out" + Calendar.getInstance().getTimeInMillis() + ".pc";
        StringBuilder pc = new StringBuilder();
        stack.stream().filter(item -> item.getPair() != -1)
                .forEach(item -> pc.append(item.toString()));
        try {
            Files.write(Paths.get(fileName), pc.toString().getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
