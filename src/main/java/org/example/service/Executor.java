package org.example.service;

import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Executor {

    private static final ThreadPoolExecutor poolExecutor = (ThreadPoolExecutor) Executors.newCachedThreadPool();

    public static void execute(Runnable job) {
        poolExecutor.submit(job);
    }

    public static void interruptAll() {
        poolExecutor.shutdownNow();
    }
}