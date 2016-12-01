package ru.fivt.dostavimvse;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by akhtyamovpavel on 01.12.16.
 */
public class Operator {
    private static Operator ourInstance = new Operator();

    public static Operator getInstance() {
        return ourInstance;
    }

    private ThreadPoolExecutor createOrderExecutor;
    private ThreadPoolExecutor changeLegExecutor;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    public ThreadPoolExecutor getChangeLegExecutor() {
        return changeLegExecutor;
    }



    private Operator() {
        createOrderExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        changeLegExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
        scheduler.scheduleAtFixedRate(new DatabaseCrawler(), 10, 10, TimeUnit.SECONDS);
    }
}
