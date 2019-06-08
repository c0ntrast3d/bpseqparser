package it.unicam.pbparser.io;

import it.unicam.pbparser.entities.BPair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static it.unicam.pbparser.utils.FileUtils.generateFileName;

public class OrderedPairsWriter {
    public static boolean write(String name, String heading, String primaryStructure, List<BPair> list) {

        String fileName = generateFileName(Paths.get(""), name, "-ass");
        Path directory = Paths.get(Paths.get("").toAbsolutePath().toString() + "/out");
        StringBuilder pairs = new StringBuilder();
        list.forEach(pairs::append);
        if (pairs.length() > 0) {
            pairs.deleteCharAt(pairs.length() - 1);
        }
        try {
            System.out.println(String.format("WRITING ORDERED :: %s", Thread.currentThread().getName()));
            if (Files.notExists(directory)) {
                Files.createDirectories(directory);
            }
            Files.write(Paths.get(fileName), heading.concat("\n").getBytes());
            Files.write(Paths.get(fileName), primaryStructure.concat("\n\n").getBytes(), StandardOpenOption.APPEND);
            Files.write(Paths.get(fileName), pairs.toString().getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
