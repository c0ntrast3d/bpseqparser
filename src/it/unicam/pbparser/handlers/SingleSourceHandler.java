package it.unicam.pbparser.handlers;

import it.unicam.pbparser.Executors;
import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.entities.ReaderOutput;
import it.unicam.pbparser.io.OrderedPairsWriter;
import it.unicam.pbparser.mappers.PairsList;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static it.unicam.pbparser.io.BpseqReader.read;


public class SingleSourceHandler {

    private static CompletableFuture<?> getOrderedPairsThenWrite(ReaderOutput input) {
        return CompletableFuture.supplyAsync(() -> PairsList.fromBPair(input.getPairs()))
                .thenApplyAsync((pairsList) ->
                        pairsList
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

    private static CompletableFuture<?> getParallelComponentsThenWrite(ReaderOutput input) {
        return CompletableFuture.supplyAsync(() -> PairsList.fromBPair(input.getPairs()));
    }

    private static CompletableFuture<?> getSizeThenWrite(ReaderOutput input) {
        return CompletableFuture.supplyAsync(() -> PairsList.fromBPair(input.getPairs()));
    }

    public static void process(String fileName) {
        final AtomicReference<ReaderOutput> output = new AtomicReference<>();
        CompletableFuture.supplyAsync(() -> read(fileName), Executors.singleThread())
                .thenComposeAsync(readerOutput ->
                                CompletableFuture.allOf(
/*                                CompletableFuture.supplyAsync(() -> Compressor.compress(readerOutput.getPairs()))
                                        .thenApply((compressed) -> Writer.write(readerOutput.getHeading(), readerOutput.getPrimaryStructure(), compressed))*/
                                        getOrderedPairsThenWrite(readerOutput))
                )
                .join();
    }

}
