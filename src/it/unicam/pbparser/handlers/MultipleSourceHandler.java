package it.unicam.pbparser.handlers;

import java.util.Set;

public class MultipleSourceHandler {
    public static void processDirectory(Set<String> files) {
        files.forEach(SingleSourceHandler::processFile);
    }
}
