package it.unicam.pbparser;

import it.unicam.pbparser.entities.BPair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Calendar;
import java.util.List;

class Writer {
    static void write(String heading, String primaryStructure, List<BPair> list) {
        Path currentRelativePath = Paths.get("");
        String fileName = currentRelativePath.toAbsolutePath().toString() + "/out" + Calendar.getInstance().getTimeInMillis() + ".txt";
        StringBuilder pairs = new StringBuilder();
        list.forEach(pairs::append);
        if (pairs.length() > 0) {
            pairs.deleteCharAt(pairs.length() - 1);
        }
        try {
            Files.write(Paths.get(fileName), heading.getBytes());
            Files.write(Paths.get(fileName), primaryStructure.getBytes(), StandardOpenOption.APPEND);
            Files.write(Paths.get(fileName), pairs.toString().getBytes(), StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
