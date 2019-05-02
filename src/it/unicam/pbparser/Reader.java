package it.unicam.pbparser;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.entities.ReaderOutput;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static it.unicam.pbparser.Utils.tryParseBPair;

class Reader {

    static ReaderOutput read(String path) {
        StringBuilder heading = new StringBuilder();
        StringBuilder primaryStructure = new StringBuilder();
        List<BPair> pairs = new ArrayList<>();

        try {
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
/*            result = br.lines().map(
                    Utils::tryParseBPair
            ).collect(Collectors.toList());
            br.close();*/
        } catch (IOException io) {
            io.printStackTrace();
        }
        heading.append("\n");
        primaryStructure.append("\n\n");
        return new ReaderOutput(heading.toString(),
                primaryStructure.toString(),
                pairs);
    }
}
