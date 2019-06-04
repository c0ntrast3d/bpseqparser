package it.unicam.pbparser.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathUtils {
    public static boolean isDirectory(String path) throws FileNotFoundException {
        final Path currentRelativePath = Paths.get("");
        final String fileName = new StringBuilder()
                .append(currentRelativePath.toAbsolutePath().toString())
                .append("/")
                .append(path)
                .toString();
        final Path file = new File(fileName).toPath();
        if (!Files.exists(file)) {
            throw new FileNotFoundException();
        }
        return Files.isDirectory(file);
    }
}
