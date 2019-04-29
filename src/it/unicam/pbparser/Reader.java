package it.unicam.pbparser;

import it.unicam.pbparser.entities.BPair;
import it.unicam.pbparser.exceptions.WrongFormatException;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static it.unicam.pbparser.Alphabet.isValidBase;
import static it.unicam.pbparser.Utils.tryParseInt;

class Reader {


    private static boolean isRightFormat(String[] splitLine) {
        return (tryParseInt(splitLine[0]) && isValidBase(splitLine[1]) && tryParseInt(splitLine[2]));
    }

    private static BPair createFromLine(String line) throws RuntimeException {
        String[] splitLine = line.split(" ");
        if (isRightFormat(splitLine))
            return new BPair(Integer.parseInt(splitLine[0]), splitLine[1].charAt(0), Integer.parseInt(splitLine[2]));
        else throw new WrongFormatException(line);
    }

    static List<BPair> read(String path) {
        ArrayList<BPair> res = null;
        try {
            BufferedReader br = Files.newBufferedReader(Paths.get(path));
            Stream<BPair> lines = br.lines().map(
                    line -> {
                        try {
                            return createFromLine(line);
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
            );
            res = lines.collect(Collectors.toCollection(ArrayList::new));
            lines.close();
        } catch (IOException io) {
            io.printStackTrace();
        }
        return res;
    }
}
