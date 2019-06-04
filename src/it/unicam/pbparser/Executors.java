package it.unicam.pbparser;

import java.util.concurrent.Executor;

public class Executors {

    public static Executor multiThread(int threads) {
        return java.util.concurrent.Executors.newFixedThreadPool(threads, r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }

    public static Executor singleThread() {
        return java.util.concurrent.Executors.newSingleThreadExecutor(r -> {
            Thread t = new Thread(r);
            t.setDaemon(true);
            return t;
        });
    }
}
