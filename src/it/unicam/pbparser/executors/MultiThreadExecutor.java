package it.unicam.pbparser.executors;

import java.util.concurrent.Executor;

public class MultiThreadExecutor {

    public static Executor get(int threads) {
        return java.util.concurrent.Executors.newFixedThreadPool(threads, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

}
