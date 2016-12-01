package ru.fivt.dostavimvse;

import org.hibernate.Session;
import ru.fivt.dostavimvse.models.Order;
import ru.fivt.dostavimvse.models.OrderStatus;
import ru.fivt.dostavimvse.models.Route;
import ru.fivt.dostavimvse.models.RouteLeg;

/**
 * Created by akhtyamovpavel on 01.12.16.
 */
public class OrderChangeLegTask implements Runnable {

    private Order order;

    public OrderChangeLegTask(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
        Route route = order.getRoute();
        RouteIterator routeIterator = new RouteIterator(route);
        Session session = HibernateSessionFactory.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        // TODO: write current session in RouteLeg
        if (routeIterator.hasNext()) {
            RouteLeg newLeg = routeIterator.next();
//            order.setStatus(OrderStatus.MOVING);
        } else {
//            order.setStatus(OrderStatus.READY);

            session.save(order);

        }

        session.getTransaction().commit();
        session.close();

    }
}
