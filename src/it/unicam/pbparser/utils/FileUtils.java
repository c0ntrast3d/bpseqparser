package it.unicam.pbparser.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class FileUtils {
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

    public static Set<String> getFilesList(String dir) {
        Set<String> fileList = new HashSet<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
            for (Path path : stream) {
                if (!Files.isDirectory(path)) {
                    fileList.add(generatePath(path, dir));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileList;
    }

    public static boolean isBpseq(String fileName) {
        return fileName.substring(fileName.lastIndexOf('.') + 1).equals("bpseq");
    }

    public static String generateFileName(Path path, String name, String postfix) {
        return new StringBuilder()
                .append(Paths.get("").toAbsolutePath())
                .append("/")
                .append("out")
                .append("/")
                .append(name)
                .append(postfix)
                .append(".txt")
                .toString();
    }

    public static String generatePath(Path path, String dir) {
        return new StringBuilder()
                .append(Paths.get("").toAbsolutePath())
                .append("/")
                .append(dir)
                .append("/")
                .append(path.getFileName())
                .toString();
    }
}
