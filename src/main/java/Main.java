import org.hibernate.Query;
import org.hibernate.Session;
import ru.fivt.dostavimvse.HibernateSessionFactory;
import ru.fivt.dostavimvse.RouteIterator;
import ru.fivt.dostavimvse.models.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by akhtyamovpavel on 29.11.16.
 */
public class Main {

    public static void main(final String[] args) throws Exception {
        final Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {

//            session.beginTransaction();
//            for (int i = 0; i < 5; ++i) {
//                Client firstClient = new Client();
//                session.save(firstClient);
//            }
//            session.getTransaction().commit();


//            session.beginTransaction();
//            Client firstClient = new Client();
//            Order order = new Order();
//            Product product = new Product();
//            product.setWeight(0.8);
//            product.setPrice(0.4);
//
//            product.setOrder(order);
//
//
//            Set<Product> products = order.getProducts();
//            products.add(product);
//
//            order.setProducts(products);
//            order.setStartDate(LocalDateTime.now());
//            order.setStartVertex(0);
//            order.setEndVertex(9);
//
//            order.setClient(firstClient);
//            order.setOrderStatus(OrderStatus.WAIT_CHANGE);
//            order.setOrderType(OrderType.TIME);
//            order.setReceiver(session.get(Client.class, 11));
//            Set<Order> orders = firstClient.getOrders();
//            orders.add(order);
//            firstClient.setOrders(orders);
//            session.save(firstClient);
//            session.getTransaction().commit();


//            session.beginTransaction();
//            // up some legs in graph
//
//            for (int i = 0; i < 10; ++i) {
//                for (int j = i; j < 10; ++j) {
//                    Leg leg = new Leg();
//                    leg.setStartVertex(i);
//                    leg.setEndVertex(j);
//                    leg.setBasePrice(1.0);
//                    leg.setMaxWeight(1.0);
//                    leg.setLegType(LegType.TRAIN);
//                    leg.setSendTime(20);
//                    session.save(leg);
//                }
//            }
//            session.getTransaction().commit();

            session.beginTransaction();
            Order order = session.get(Order.class, 8);

            Route route = new Route();

            order.setRoute(route);
            route.setOrder(order);

            Query query = session.createQuery("FROM Leg");
            List<Leg> legs = query.list();

            boolean found[] = new boolean[10];
            for (int i = 0; i < 10; ++i) {
                found[i] = false;
            }

            Set<RouteLeg> routeLegs = new HashSet<>();
            for (Leg leg: legs) {
                int startVertex = leg.getStartVertex();
                int endVertex = leg.getEndVertex();
                if (endVertex == startVertex + 1) {
                    if (!found[startVertex]) {
                        found[startVertex] = true;
                        RouteLeg routeLeg = new RouteLeg();
                        routeLeg.setLeg(leg);
                        routeLeg.setRoute(route);
                        routeLegs.add(routeLeg);
                    }
                }
            }
            route.setRouteLegs(routeLegs);

            session.saveOrUpdate(route);
            session.saveOrUpdate(order);

            session.getTransaction().commit();
//
//            RouteIterator routeIterator = new RouteIterator(order, false);
//
//            while(routeIterator.hasNext()) {
//                RouteLeg next = routeIterator.next();
//                System.out.println("VERTEX");
//                System.out.println(next.getLeg().getStartVertex());
//                System.out.println(next.getLeg().getEndVertex());
//            }



//            Leg leg = session.get(Leg.class, 1);
//
//
//            RouteLeg routeLeg = new RouteLeg();
//            routeLeg.setStartTime(LocalDateTime.now());
//            routeLeg.setEndTime(LocalDateTime.now().plusSeconds(leg.getSendTime()));
//            routeLeg.setLeg(session.get(Leg.class, 1));
//            routeLeg.setRoute(route);
//
//            Set<RouteLeg> routeLegSet = route.getRouteLegs();
//            routeLegSet.add(routeLeg);
//
//            route.setLegs(routeLegSet);


            // Route route = session.get(Route.class, 2);
//            session.saveOrUpdate(order);
//            session.save(route);
//            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
            HibernateSessionFactory.shutdown();
        }
    }
}