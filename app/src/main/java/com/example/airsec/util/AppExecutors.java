package com.example.airsec.util;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/** Ejecutores simples para I/O y UI (pasa runOnUiThread desde la Activity cuando lo necesites). */
public final class AppExecutors {
    private static volatile AppExecutors INSTANCE;

    private final ExecutorService io;      // Base de datos / red
    private final ExecutorService compute; // Tareas pesadas

    private AppExecutors() {
        this.io = Executors.newFixedThreadPool(2);
        this.compute = Executors.newSingleThreadExecutor();
    }

    public static AppExecutors get() {
        if (INSTANCE == null) {
            synchronized (AppExecutors.class) {
                if (INSTANCE == null) INSTANCE = new AppExecutors();
            }
        }
        return INSTANCE;
    }

    public Executor io() { return io; }
    public Executor compute() { return compute; }
}
