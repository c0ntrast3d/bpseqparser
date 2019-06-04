package it.unicam.pbparser.mappers;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.exceptions.UnmatchedBasePairException;

import java.util.List;
import java.util.stream.Collectors;

public class PairsList {
    public static List<BPair> fromBPair(List<BPair> list) {

        return list
                .stream()
                .peek(item -> {
                    if (notPairedOrMarked(item.getPair())) {
                        final BPair pair = list.get(pairsIndex(item));
                        if (pair.getPair() == item.getIndex()) {
                            markForRemoval(pair);
                        } else {
                            throw new UnmatchedBasePairException(item.getIndex(), pair.getIndex());
                        }
                    }
                })
                .filter(item -> notPairedOrMarked(item.getPair()))
                .collect(Collectors.toList());
    }

    private static int pairsIndex(BPair bpair) {
        return bpair.getPair() - 1;
    }

/*
    private static Function<BPair, Integer> pairIndex = (pair) -> pair.getPair() - 1;
*/

    private static boolean notPairedOrMarked(int pair) {
        return pair != 0 && pair != -1;
    }

    private static void markForRemoval(BPair pair) {
        pair.setPair((short) -1);
    }
}
