package it.unicam.pbparser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

class Compressor {

    private static boolean isPaired(int first, int second) throws IOException {
        if (first == second) return true;
        else throw new IOException();
    }

    private static boolean validIndex(int index) {
        return index != -1;
    }

    private static boolean isPairedSimple(int first, int second) {
        return first == second;
    }


    static List<BPair> compress(List<BPair> list) {
        AtomicInteger indexHolder = new AtomicInteger(0);
        AtomicInteger sizeHolder = new AtomicInteger(list.size());
        List<BPair> listCopy = new ArrayList<>(list);
        List<BPair> filtered = list.stream()
                .filter(item ->
                        (list.get(item.getPair()).getPair() + 1 == item.getIndex())
                )
                .collect(Collectors.toList());

/*        List<BPair> test = list.stream()
                .forEach(
                        item -> {
                            final int index = indexHolder.getAndIncrement();
                            System.out.println("------");
                            System.out.println(item.getIndex() + "  " + item.getPair() + "   " + ((list.get((item.getPair())).getPair() + 1)));
                            if (item.getPair() == 0) {
                                item.setPair(-1);
                                return item;
                            } else if ((list.get(item.getPair()).getPair()) == item.getIndex()) {
                                item.setPair(-1);
                                return item;
                            }
                            return item;
                        }
                ).collect(Collectors.toList());*/

        return listCopy.stream().map(item -> {
            //if (item.getPair() == 0) item.setPair(-1);
            //if (item.getPair() != -1 && (list.get(item.getPair()).getPair()) == item.getIndex()) item.setPair(-1);
            if (item.getPair() == 0) {
                item.setPair(-1);
                return item;
            }
            if (item.getPair() != -1) {
                if ((item.getIndex() == (list.get(item.getPair() - 1).getPair()))) {
                    item.setPair(-1);
                    return item;
                }
            }
            System.out.println("List size       : " + list.size());
            System.out.println("Index           : " + indexHolder.getAndIncrement());
            System.out.println("Item index      : " + item.getIndex());
            System.out.println("Item pair       : " + item.getPair());
            if (item.getPair() != 0) {
                System.out.println("Items pair pair : " + (list.get(item.getPair() - 1).getPair()));
                System.out.println("Pair match      : " + (item.getIndex() == (list.get(item.getPair() - 1).getPair())));
            }
            //System.out.println("Items pair pair : " + (list.get(item.getPair()).getPair()));
            //System.out.println("Pair match      : " + ((int) item.getIndex() == (list.get(item.getPair()).getPair())));
            System.out.println("-----------------------------------------------------------------");
            return item;
        }).collect(Collectors.toList());
        //test.forEach(BPair::print);

        //filtered.forEach(item -> System.out.println(String.format("Index: %d :: Base %s :: Pair %d :: isPaired : %s", item.getIndex(), item.getBaseName(), item.getPair(), isPairedSimple(list.get(item.getPair()).getPair() + 1, item.getIndex()))));
        //filtered.forEach(item -> System.out.println(isPairedSimple(list.get(item.getPair()).getPair() + 1, item.getIndex())));

/*        for (int counter = 0; counter < listCopy.size(); counter++) {
            int secong = listCopy.get(listCopy.get(counter).getPair()).getPair() + 1;
            final int index = indexHolder.getAndIncrement();
            System.out.println(String.format("Pair : %d :: Second Pair : %d", listCopy.get(counter).getIndex(), secong));
            System.out.println(index);
            if (list.get(counter).getPair() == 0) {
                System.out.println("REMOVING UNCONNECTED");
                list.get(counter).setPair(-1);

            } else if (isPairedSimple(list.get(counter).getPair(), secong)) {
                System.out.println("REMOVING DUPLICATE");
                list.get(secong -1).setPair(-1);
            }
        }
        System.out.println("PRINTING FILTERED");
        listCopy.forEach(BPair::print);*/

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

