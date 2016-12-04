package ru.fivt.dostavimvse;

import org.hibernate.Session;
import ru.fivt.dostavimvse.models.Order;
import ru.fivt.dostavimvse.models.OrderStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
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
        //TODO: On startup take all legs from normal DateTime
    }

    void createRoute(Order order) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        order.setStartDate(LocalDateTime.now());
//        order.setStatus(OrderStatus.WAIT_CREATE);
    }
}
