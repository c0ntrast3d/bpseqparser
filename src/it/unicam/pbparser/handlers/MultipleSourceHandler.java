package it.unicam.pbparser.handlers;

import it.unicam.pbparser.utils.FileUtils;

import java.util.Set;

public class MultipleSourceHandler {
    public static void processDirectory(Set<String> files) {
        files.forEach(file -> {
                    if (FileUtils.isBpseq(file))
                        SingleSourceHandler.processFile(file);
                }
        );
    }
}
