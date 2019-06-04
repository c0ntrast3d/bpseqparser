package it.unicam.pbparser;

import it.unicam.pbparser.utils.PathUtils;

import java.io.FileNotFoundException;

import static it.unicam.pbparser.handlers.SingleSourceHandler.process;

class Controller {

    static void start(String input) {
        try {
            if (PathUtils.isDirectory(input)) {
                System.out.println("PROCESSING DIRECTORY");
            } else {
                process(input);
            }
        } catch (FileNotFoundException e) {
            System.out.println("INCORRECT INPUT");
        }
    }
}
