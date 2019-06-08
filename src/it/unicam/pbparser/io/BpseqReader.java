package it.unicam.pbparser.io;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.entities.ReaderOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static it.unicam.pbparser.utils.BPairParser.tryParseBPair;


public class BpseqReader {


    public static ReaderOutput read(String path) {
        StringBuilder heading = new StringBuilder();
        StringBuilder primaryStructure = new StringBuilder();
        String fileName = path.split("\\.")[0];
        List<BPair> pairs = new ArrayList<>();

        try {
            System.out.println(String.format("READING :: %s", Thread.currentThread().getName()));

            BufferedReader br = Files.newBufferedReader(Paths.get(path));
            br.lines().forEach(line -> {
                if (line.contains("#")) {
                    heading.append(line).append("\n");
                } else {
                    final BPair pair = tryParseBPair(line);
                    pairs.add(pair);
                    primaryStructure.append(pair.getBaseName());
                }
            });
/*            Files.newBufferedReader(Paths.get(path))
                    .lines()
                    .forEach(item ->
                            BPairParser.parse.apply(item)
                                    .thenApply(pairs::add)
                                    .exceptionally(e -> {
                                        System.out.println(e.getMessage());
                                        return true;
                                    })
                    );*/
            br.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
        heading.append("\n");
        primaryStructure.append("\n\n");

        return new ReaderOutput(
                fileName,
                heading.toString(),
                primaryStructure.toString(),
                pairs);
    }

    public static CompletableFuture<ReaderOutput> readCF(String file) {
        return CompletableFuture.supplyAsync(() -> BpseqReader.read(file));
    }
}
