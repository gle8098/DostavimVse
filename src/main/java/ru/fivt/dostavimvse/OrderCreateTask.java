package ru.fivt.dostavimvse;

import org.hibernate.Session;
import ru.fivt.dostavimvse.models.Order;
import ru.fivt.dostavimvse.models.OrderType;
import ru.fivt.dostavimvse.models.Route;

/**
 * Created by akhtyamovpavel on 01.12.16.
 */
public class OrderCreateTask implements Runnable {
    private Order order;
    private OptimalSolver solver;

    public OrderCreateTask(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        OptimalSolver solver = null;
        if (order.getOrderType() == OrderType.TIME) {
            solver = new OptimalTimeSolver();
        } else if (order.getOrderType() == OrderType.PRICE) {
            solver = new OptimalPriceSolver();
        } else {
            return;
        }
        Route route = solver.buildOptimalRoute(order);
        order.setRoute(route);
        route.setOrder(order);

        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        session.save(route);

        session.beginTransaction().commit();
    }

}
