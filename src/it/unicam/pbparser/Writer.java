package it.unicam.pbparser;

import it.unicam.pbparser.entities.BPair;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

class Writer {
    static boolean write(List<BPair> list) throws IOException {
        Path currentRelativePath = Paths.get("");
        String fileName = currentRelativePath.toAbsolutePath().toString() + "/out" + Calendar.getInstance().getTimeInMillis() + ".txt";
/*        Path currentRelativePath = Paths.get("");
        String fileName = currentRelativePath.toAbsolutePath().toString() + "/out" + Calendar.getInstance().getTimeInMillis() + ".txt";
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }*/
        StringBuilder buffer = new StringBuilder();

/*       list.stream().filter(item -> item.getPair() != -1).forEach(pair ->
                buffer.append(String.format("%d %d;", pair.getIndex(), pair.getPair()))
        );*/

/*
        List<BPair> tmp = list.parallelStream().filter(item -> item.getPair() != -1).collect(Collectors.toList());
*/

        list.forEach(buffer::append);

/*        for (BPair pair : list) {
            if (pair.getPair() != -1) {
                buffer.append(String.format("%d %d;", pair.getIndex(), pair.getPair()));
            }
        }*/
        Files.write(Paths.get(fileName), buffer.toString().getBytes());
/*        Objects.requireNonNull(pw).println(buffer.toString());
        Objects.requireNonNull(pw).close();*/
        return true;
    }
}
