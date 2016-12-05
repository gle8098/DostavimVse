package ru.fivt.dostavimvse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.fivt.dostavimvse.models.Order;
import ru.fivt.dostavimvse.models.OrderStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
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
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT o FROM Order o INNER JOIN o.route AS r LEFT JOIN r.currentLeg AS  rl WHERE o.orderStatus = :order_status " +
                "AND (r.currentLeg is NULL OR rl.endTime > :end_time)");
        query.setParameter("order_status", OrderStatus.WAIT_CHANGE);
        query.setParameter("end_time", LocalDateTime.now());

        List<Order> orders = (List<Order>)query.list();


        for (Order order: orders) {
            System.out.println("OK");
            changeRouteLeg(order);
        }

        session.close();
        //TODO: On startup take all legs from normal DateTime
    }

    void createRoute(Order order) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        session.beginTransaction();

        order.setStartDate(LocalDateTime.now());
//        order.setStatus(OrderStatus.WAIT_CREATE);
    }

    public void changeRouteLeg(Order order) {
        changeLegExecutor.execute(new OrderChangeLegTask(order));
    }
}
