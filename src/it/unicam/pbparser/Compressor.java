package it.unicam.pbparser;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class Compressor {

    private static boolean isPaired(int first, int second) throws IOException {
        if (first == second) return true;
        else throw new IOException();
    }

    private static boolean isPairedSimple(int first, int second) {
        return first == second;
    }


        static List<BPair> compress(List<BPair> list) {
            AtomicInteger indexHolder = new AtomicInteger(1);

            List<BPair> filtered = list.stream()
                    .filter(item ->
                            (list.get(item.getPair()).getPair() + 1 == item.getIndex())
                    )
                    .collect(Collectors.toList());
            //filtered.forEach(item -> System.out.println(String.format("Index: %d :: Base %s :: Pair %d", item.getIndex(), item.getBaseName(), item.getPair())));
            //filtered.forEach(item -> System.out.println(isPairedSimple(list.get(item.getPair()).getPair() + 1, item.getIndex())));
            return filtered;
        }
/*    static List<BPair> compress(List<BPair> list) {
        AtomicInteger indexHolder = new AtomicInteger(1);

        List<BPair> filtered = list.stream()
                .map(item -> {
                            try {
                                final int index = indexHolder.getAndIncrement();
                                if (isPaired(list.get(item.getPair()).getPair() + 1, index)) {
                                    return item;
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                                throw new RuntimeException();
                            }
                            return new BPair(-1, 'E', -1);
                        }
                )
                .collect(Collectors.toList());
        //filtered.forEach(item -> System.out.println(String.format("Index: %d :: Base %s :: Pair %d", item.getIndex(), item.getBaseName(), item.getPair())));
        filtered.forEach(item -> System.out.println(isPairedSimple(list.get(item.getPair()).getPair() + 1, item.getIndex())));
        return filtered;
    }*/
}

