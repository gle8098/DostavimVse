import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.ConstraintViolationException;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.springframework.web.bind.MethodArgumentNotValidException;
import ru.fivt.dostavimvse.HibernateSessionFactory;
import ru.fivt.dostavimvse.models.*;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;
import java.util.Set;

/**
 * Created by akhtyamovpavel on 29.11.16.
 */
public class Main {

    public static void main(final String[] args) throws Exception {
        final Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
//            session.beginTransaction();
//
//            Client firstClient = new Client(); // session.get(Client.class, 3);
//
//            Order order = new Order();
//
//            Product product = new Product();
//            product.setWeight(0.8);
//            product.setPrice(0.4);
//            product.setOrder(order);
//
//            Set<Product> products = order.getProducts();
//            products.add(product);
//
//            order.setProducts(products);
//            order.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
//            order.setStartVertex(0);
//            order.setEndVertex(1);
//
//            order.setClient(firstClient);
//            Set<Order> orders = firstClient.getOrders();
//            orders.add(order);
//            firstClient.setOrders(orders);
//
//            session.save(firstClient);
//            session.getTransaction().commit();

            session.beginTransaction();
            Leg leg = new Leg();
            try {
                leg.setStartVertex(0);
                leg.setEndVertex(0);
                leg.setMaxWeight(-0.8);
                leg.setLegType(LegType.TRAIN);
            } catch (Exception e) {
                e.printStackTrace();
            }

            session.save(leg);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }
}