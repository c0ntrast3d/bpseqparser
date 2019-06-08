package it.unicam.pbparser.io;

import it.unicam.pbparser.entities.ParallelComponent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static it.unicam.pbparser.utils.FileUtils.generateFileName;

public class ParallelComponentsWriter {
    public static boolean write(List<ParallelComponent> list, String name) {
        String fileName = generateFileName(Paths.get(""), name, "-pc");
        StringBuilder pc = new StringBuilder();
        list.forEach(item -> pc.append(item.toString()));
        try {
            System.out.println(String.format("WRITING PC :: %s", Thread.currentThread().getName()));
            Files.write(Paths.get(fileName), pc.toString().getBytes(), StandardOpenOption.CREATE);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
