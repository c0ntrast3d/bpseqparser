package it.unicam.pbparser.handlers;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.entities.ReaderOutput;
import it.unicam.pbparser.executors.MultiThreadExecutor;
import it.unicam.pbparser.executors.SingleThreadExecutor;
import it.unicam.pbparser.io.OrderedPairsWriter;
import it.unicam.pbparser.io.ParallelComponentsWriter;
import it.unicam.pbparser.mappers.Pairs;
import it.unicam.pbparser.mappers.ParallelComponents;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import static it.unicam.pbparser.io.BpseqReader.read;


public class SingleSourceHandler {

    public static void processFile(String fileName) {
        CompletableFuture.supplyAsync(() -> read(fileName), SingleThreadExecutor.get())
                .thenComposeAsync(readerOutput ->
                                CompletableFuture.allOf(
/*                                CompletableFuture.supplyAsync(() -> Compressor.compress(readerOutput.getPairs()))
                                        .thenApply((compressed) -> Writer.write(readerOutput.getHeading(), readerOutput.getPrimaryStructure(), compressed))*/
                                        getOrderedPairsThenWrite(readerOutput),
/*                                                .thenComposeAsync(orderedPairs -> getParallelComponentsThenWrite(
                                                        orderedPairs,
                                                        readerOutput.getPrimaryStructure().length() - 2,
                                                        readerOutput.getFileName()
                                                ))*/
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
    }

    private static CompletableFuture<?> getOrderedPairsThenWrite(ReaderOutput input) {
        return CompletableFuture.supplyAsync(() -> Pairs.fromBPair(input.getPairs()), MultiThreadExecutor.get(4))
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
/*                .thenApply(compressed -> {
                    final List<BPair> sorted = compressed.stream().sorted(Comparator.comparing(BPair::getPair)).collect(Collectors.toList());
                    return Writer.write(input.getHeading(),
                            input.getPrimaryStructure(),
                            sorted);
                });*/
    }

    private static CompletableFuture<?> getParallelComponentsThenWrite(List<BPair> pairs, int primaryLength, String fileName) {
        return CompletableFuture.supplyAsync(() -> Pairs.fromBPair(pairs), MultiThreadExecutor.get(4))
                .thenApplyAsync((orderedPairs ->
                        ParallelComponents.generate(orderedPairs, primaryLength)))
                .thenApply((components) -> ParallelComponentsWriter.write(components, fileName));
    }

    private static CompletableFuture<?> getSizeThenWrite(ReaderOutput input) {
        return null;
    }


}