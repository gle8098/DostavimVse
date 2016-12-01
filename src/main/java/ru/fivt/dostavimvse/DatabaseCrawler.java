package ru.fivt.dostavimvse;

import org.hibernate.Query;
import org.hibernate.Session;
import ru.fivt.dostavimvse.models.Order;

import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by akhtyamovpavel on 01.12.16.
 */
public class DatabaseCrawler implements Runnable {

    @Override
    public void run() {
        ThreadPoolExecutor executor = Operator.getInstance().getChangeLegExecutor();
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Order order WHERE order.orderStatus = fkdlfklfd");

        List queryList = query.list();
        for (Object object : queryList) {
            Order order = (Order)object;
            executor.execute(new OrderChangeLegTask(order));
//            Operator.getInstance().getChangeLegExecutor()
        }
    }
}
