package com.brainetrainer.taskmanager.db;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AppExecuted {
    private static AppExecuted instance;
    private final Executor mainIO;
    private final Executor secondIO;

    public AppExecuted(Executor mainIO, Executor secondIO) {
        this.mainIO = mainIO;
        this.secondIO = secondIO;
    }

    public static AppExecuted getInstance() {
        if (instance == null)
            instance = new AppExecuted(new MainThreadHandler(), Executors.newSingleThreadExecutor());
        return instance;
    }

    public static class MainThreadHandler implements Executor {
        private Handler mainHandler = new Handler(Looper.getMainLooper());

        @Override
        public void execute(Runnable runnable) {
            mainHandler.post(runnable);
        }
    }

    public Executor getMainIO() {
        return mainIO;
    }

    public Executor getSecondIO() {
        return secondIO;
    }
}
