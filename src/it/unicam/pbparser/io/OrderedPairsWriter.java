package it.unicam.pbparser.io;

import it.unicam.pbparser.entities.BPair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class OrderedPairsWriter {
    public static boolean write(String name, String heading, String primaryStructure, List<BPair> list) {

        String fileName = generateFileName(Paths.get(""), name);
        StringBuilder pairs = new StringBuilder();

        list.forEach(pairs::append);
        if (pairs.length() > 0) {
            pairs.deleteCharAt(pairs.length() - 1);
        }
        try {
            Files.write(Paths.get(fileName), heading.getBytes());
            Files.write(Paths.get(fileName), primaryStructure.getBytes(), StandardOpenOption.APPEND);
            Files.write(Paths.get(fileName), pairs.toString().getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static String generateFileName(Path path, String name) {
        return new StringBuilder()
                .append(path.toAbsolutePath().toString())
                .append("/")
                .append(name)
                .append("-aas")
                .append(".txt")
                .toString();
    }
}
