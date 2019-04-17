package it.unicam.pbparser;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

class Writer {
    static boolean write(List<BPair> list) {
        Path currentRelativePath = Paths.get("");
        String fileName = currentRelativePath.toAbsolutePath().toString() + "/out" + Calendar.getInstance().getTimeInMillis() + ".txt";
        PrintWriter pw;
        try {
            pw = new PrintWriter(new FileOutputStream(fileName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        for (BPair pair : list) {
            //pair.print();
            if (pair.getPair() != -1)
                Objects.requireNonNull(pw).println(String.format("%d %d;", pair.getIndex(), pair.getPair()));
        }
        Objects.requireNonNull(pw).close();
        return true;
    }
}
