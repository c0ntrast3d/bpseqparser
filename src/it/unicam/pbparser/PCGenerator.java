package it.unicam.pbparser;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.entities.ParallelComponent;
import javafx.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

public class PCGenerator {
    public static List<ParallelComponent> generate(List<BPair> input, int primaryLength) {
        int[] accumulator = new int[primaryLength];
        Iterator<BPair> iterator = input.iterator();
        List<BPair> copy = new ArrayList<BPair>(input);
        for (BPair pair : copy) {
            accumulator[pair.getIndex() - 1] = pair.getPair();
            accumulator[pair.getPair() - 1] = -1;
            if ((pair.getIndex() - 1) != 0 && pair.getIndex() != primaryLength) {
                if (accumulator[pair.getIndex() - 2] > pair.getPair()) {
                    accumulator[pair.getIndex() - 1] = accumulator[pair.getIndex() - 2];
                }
                if (accumulator[pair.getIndex()] < accumulator[pair.getIndex() - 1] && accumulator[pair.getIndex()] != 0) {
                    accumulator[pair.getIndex()] = accumulator[pair.getIndex() - 1];
                }
            } else {
                if (pair.getIndex() - 1 == 0) {
                    if (accumulator[pair.getIndex()] < accumulator[pair.getIndex() - 1] && accumulator[pair.getIndex()] != 0) {
                        accumulator[pair.getIndex()] = accumulator[pair.getIndex() - 1];
                    }
                } else {
                    if (accumulator[pair.getIndex() - 2] > pair.getPair()) {
                        accumulator[pair.getIndex() - 1] = accumulator[pair.getIndex() - 2];
                    }
                }
            }

        }

        Arrays.stream(accumulator).forEach(System.out::println);


        return null;
    }
}
