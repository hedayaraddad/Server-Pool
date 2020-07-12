package com.service.serverPool.util.config;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PoolExecuter {
    public static final int MAX_T = 10 ;
    public static ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
}
