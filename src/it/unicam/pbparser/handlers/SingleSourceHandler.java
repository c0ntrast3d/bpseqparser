package it.unicam.pbparser.handlers;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.entities.ReaderOutput;
import it.unicam.pbparser.executors.MultiThreadExecutor;
import it.unicam.pbparser.io.OrderedPairsWriter;
import it.unicam.pbparser.io.ParallelComponentsWriter;
import it.unicam.pbparser.mappers.Pairs;
import it.unicam.pbparser.mappers.ParallelComponents;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static it.unicam.pbparser.io.BpseqReader.read;


public class SingleSourceHandler {

    private static AtomicReference<ReaderOutput> readerOutputAtomicReference = new AtomicReference<>();

    /*    public static void processFile(String fileName) {
            CompletableFuture.supplyAsync(() -> read(fileName), SingleThreadExecutor.get())
                    .thenComposeAsync(readerOutput ->
                                    CompletableFuture.allOf(
        *//*                                CompletableFuture.supplyAsync(() -> Compressor.compress(readerOutput.getPairs()))
                                            .thenApply((compressed) -> Writer.write(readerOutput.getHeading(), readerOutput.getPrimaryStructure(), compressed))*//*
                                        getOrderedPairsThenWrite(readerOutput),
    *//*                                                .thenComposeAsync(orderedPairs -> getParallelComponentsThenWrite(
                                                            orderedPairs,
                                                            readerOutput.getPrimaryStructure().length() - 2,
                                                            readerOutput.getFileName()
                                                    ))*//*
                                        getParallelComponentsThenWrite(
                                                readerOutput.getPairs(),
                                                readerOutput.getPrimaryStructure().length() - 2,
                                                readerOutput.getFileName()
                                                // TODO remove newlines from reader output
                                        )
                                )
                )
                .exceptionally((exception) -> {
                    System.out.println(exception.getMessage());
                    exception.printStackTrace();
                    return null;
                })
                .join();
    }*/
    public static void processFile(String fileName) {
        CompletableFuture.supplyAsync(() -> read(fileName), MultiThreadExecutor.get(4))
                .thenApplyAsync(output -> {
                    readerOutputAtomicReference.set(output);
                    return Pairs.fromBPair(output.getPairs(), output.getFileName());
                })
                .thenComposeAsync(compressed ->
                                CompletableFuture.allOf(
                                        orderThenWrite(readerOutputAtomicReference.get(), compressed),
                                        //getOrderedPairsThenWrite(readerOutput),
    /*                                                .thenComposeAsync(orderedPairs -> getParallelComponentsThenWrite(
                                                            orderedPairs,
                                                            readerOutput.getPrimaryStructure().length() - 2,
                                                            readerOutput.getFileName()
                                                    ))*/
                                        getParallelComponentsThenWrite(
                                                compressed,
                                                readerOutputAtomicReference.get().getPrimaryStructure().length(),
                                                readerOutputAtomicReference.get().getFileName()
                                        )
                                )
                )
                .exceptionally((exception) -> {
                    System.out.println(exception.getMessage());
                    exception.printStackTrace();
                    return null;
                })
                .join();
    }


/*    private static CompletableFuture<?> getOrderedPairsThenWrite(ReaderOutput input) {
        return CompletableFuture.supplyAsync(() -> Pairs.fromBPair(input.getPairs()))
                .thenApplyAsync((pairsList) -> pairsList
                        .stream()
                        .sorted(Comparator.comparing(BPair::getPair))
                        .collect(Collectors.toList()))
                .thenApply((sorted) ->
                        OrderedPairsWriter.write(
                                input.getFileName(),
                                input.getHeading(),
                                input.getPrimaryStructure(),
                                sorted));
*//*                .thenApply(compressed -> {
                    final List<BPair> sorted = compressed.stream().sorted(Comparator.comparing(BPair::getPair)).collect(Collectors.toList());
                    return Writer.write(input.getHeading(),
                            input.getPrimaryStructure(),
                            sorted);
                });*//*
    }*/

    private static CompletableFuture<?> orderThenWrite(ReaderOutput input, List<BPair> compressed) {
        return CompletableFuture.supplyAsync(() -> {
            System.out.println(String.format("SORTING :: %s", Thread.currentThread().getName()));
            return compressed
                    .stream()
                    .sorted(Comparator.comparing(BPair::getPair))
                    .collect(Collectors.toList());
        })
                .thenApply((sorted) ->
                        OrderedPairsWriter.write(
                                input.getFileName(),
                                input.getHeading(),
                                input.getPrimaryStructure(),
                                sorted));
/*                .thenApply(compressed -> {
                    final List<BPair> sorted = compressed.stream().sorted(Comparator.comparing(BPair::getPair)).collect(Collectors.toList());
                    return Writer.write(input.getHeading(),
                            input.getPrimaryStructure(),
                            sorted);
                });*/
    }

    private static CompletableFuture<?> getParallelComponentsThenWrite(List<BPair> pairs,
                                                                       int primaryLength, String fileName) {
        return CompletableFuture.supplyAsync(() -> ParallelComponents.generate(pairs, primaryLength))
                .thenApply((components) -> ParallelComponentsWriter.write(components, fileName));
/*        return CompletableFuture.supplyAsync(() ->
                ParallelComponents.generate(compressed.get(), primaryLength))
                .thenApply((components) -> ParallelComponentsWriter.write(components, fileName));*/
    }

    private static CompletableFuture<?> getSizeThenWrite(ReaderOutput input) {
        return null;
    }


}
