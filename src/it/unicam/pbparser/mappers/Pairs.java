package it.unicam.pbparser.mappers;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.exceptions.UnmatchedBasePairException;

import java.util.Comparator;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class Pairs {

    public static List<BPair> fromBPair(List<BPair> list, String fileName) {

        System.out.println(String.format("COMPRESSING :: %s", Thread.currentThread().getName()));

        return list.stream().peek(item -> {
            if (notPairedOrMarked(item.getPair())) {
                final BPair pair = list.get(pairsIndex(item));
                if (pair.getPair() == item.getIndex()) {
                    markForRemoval(pair);
                } else {
                    throw new UnmatchedBasePairException(item.getIndex(), pair.getIndex(), pair.getPair(), fileName);
                }
            }
        })
                .filter(item -> notPairedOrMarked(item.getPair()))
                .sorted(Comparator.comparing(BPair::getPair))
                .collect(toList());
    }

    private static int pairsIndex(BPair bpair) {
        return bpair.getPair() - 1;
    }

    private static boolean notPairedOrMarked(int pair) {
        return pair != 0 && pair != -1;
    }

    private static void markForRemoval(BPair pair) {
        pair.setPair((short) -1);
    }

    /* private static Function<BPair, Integer> pairIndex = (pair) -> pair.getPair() - 1; */

}
