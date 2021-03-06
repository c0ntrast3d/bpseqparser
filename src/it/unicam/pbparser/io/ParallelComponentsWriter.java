package it.unicam.pbparser.io;

import it.unicam.pbparser.entities.ParallelComponent;
import it.unicam.pbparser.utils.OutputFormatter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static it.unicam.pbparser.utils.FileUtils.generateFileName;

public class ParallelComponentsWriter {
    public static boolean write(List<ParallelComponent> list, String name) {
        String fileName = generateFileName(Paths.get(""), name, "-pc");
        Path directory = Paths.get(Paths.get("").toAbsolutePath().toString() + "/parser_output");
        String pcOutput = OutputFormatter.format(list);
        try {
            System.out.println(String.format("WRITING PC :: %s", Thread.currentThread().getName()));
            if (Files.notExists(directory)) {
                Files.createDirectories(directory);
            }
            Files.write(Paths.get(fileName), pcOutput.getBytes(), StandardOpenOption.CREATE);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
