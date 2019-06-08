package it.unicam.pbparser;

import it.unicam.pbparser.handlers.SingleSourceHandler;
import it.unicam.pbparser.utils.FileUtils;

import java.io.FileNotFoundException;

import static it.unicam.pbparser.handlers.MultipleSourceHandler.processDirectory;
import static it.unicam.pbparser.utils.FileUtils.getFilesList;

class Controller {

    static void start(String input) {
        try {
            if (FileUtils.isDirectory(input)) {
                long start = System.nanoTime();
                processDirectory(getFilesList(input));
                System.out.println("All computations made in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
            } else {
                long start = System.nanoTime();
                SingleSourceHandler.processFile(input);
                System.out.println("All computations made in " + ((System.nanoTime() - start) / 1_000_000) + " msecs");
            }
        } catch (FileNotFoundException e) {
            System.out.println("INCORRECT INPUT");
        }
    }
}
