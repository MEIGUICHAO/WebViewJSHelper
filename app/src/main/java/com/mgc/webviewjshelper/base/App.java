package com.mgc.webviewjshelper.base;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App extends Application {

    static ExecutorService singlePool;


    public static ExecutorService getSinglePool() {
        if (null == singlePool || singlePool.isShutdown() || singlePool.isTerminated()) {
            singlePool = Executors.newSingleThreadExecutor();
        }
        return singlePool;
    }
}
