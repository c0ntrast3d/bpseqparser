package it.unicam.pbparser.mappers;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.entities.WBSize;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Sizes {
    public static List<WBSize> fromPairs(List<BPair> pairs) {
        return pairs.stream()
                .map(pair -> generateWBSize.apply(pair))
                .collect(Collectors.toList());
    }

    private static Function<BPair, WBSize> generateWBSize = (pair) -> {
        final short start = pair.getIndex();
        final short end = pair.getPair();
        return new WBSize(start, end, (short) ((end - start) + 1));
    };

}
