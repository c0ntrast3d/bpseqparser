package it.unicam.pbparser.executors;

import java.util.concurrent.Executor;

public class SingleThreadExecutor {

    public static Executor get() {
        return java.util.concurrent.Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }
}
