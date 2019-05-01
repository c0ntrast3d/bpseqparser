package it.unicam.pbparser;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.exceptions.UnmatchedBasePairException;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

class Compressor {

    static List<BPair> compress(List<BPair> list) {

        return list
                .stream()
                .peek(item -> {
                    if (item.pairNotNull()) {
                        final BPair pair = list.get(item.realIndex());
                        if (pair.getPair() == item.getIndex()) {
                            pair.mark();
                        } else {
                            throw new UnmatchedBasePairException();
                        }
                    }
                })
                .sorted(Comparator.comparing(BPair::getPair))
                .filter(BPair::pairNotNull)
                .collect(Collectors.toList());
    }

}

