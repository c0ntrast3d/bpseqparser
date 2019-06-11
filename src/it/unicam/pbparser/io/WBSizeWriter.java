package it.unicam.pbparser.io;

import it.unicam.pbparser.entities.WBSize;
import it.unicam.pbparser.utils.OutputFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static it.unicam.pbparser.utils.FileUtils.generateFileName;

public class WBSizeWriter {
    public static boolean write(String name, String primaryStructure, List<WBSize> list) {

        String fileName = generateFileName(Paths.get(""), name, "-wb-size");
        Path directory = Paths.get(Paths.get("").toAbsolutePath().toString() + "/parser_output");
        String wbsOutput = OutputFormatter.format(list);

        try {
            System.out.println(String.format("WRITING WB-SIZES :: %s", Thread.currentThread().getName()));
            if (Files.notExists(directory)) {
                Files.createDirectories(directory);
            }
            Files.write(Paths.get(fileName), primaryStructure.concat("\n\n").getBytes());
            Files.write(Paths.get(fileName), wbsOutput.getBytes(), StandardOpenOption.APPEND);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
