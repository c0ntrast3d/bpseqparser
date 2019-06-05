package it.unicam.pbparser;

import it.unicam.pbparser.utils.FileUtils;

import java.io.FileNotFoundException;

import static it.unicam.pbparser.handlers.SingleSourceHandler.processFile;
import static it.unicam.pbparser.handlers.MultipleSourceHandler.processDirectory;
import static it.unicam.pbparser.utils.FileUtils.getFilesList;

class Controller {

    static void start(String input) {
        try {
            if (FileUtils.isDirectory(input)) {
                processDirectory(getFilesList(input));
            } else {
                processFile(input);
            }
        } catch (FileNotFoundException e) {
            System.out.println("INCORRECT INPUT");
        }
    }
}
