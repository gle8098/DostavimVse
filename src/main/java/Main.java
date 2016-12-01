import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.metadata.ClassMetadata;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.fivt.dostavimvse.HibernateSessionFactory;
import ru.fivt.dostavimvse.models.Client;
import ru.fivt.dostavimvse.models.Leg;
import ru.fivt.dostavimvse.models.Order;
import ru.fivt.dostavimvse.models.Product;

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
            session.beginTransaction();

            Client firstClient = new Client(); // session.get(Client.class, 3);

            Order order = new Order();

            Product product = new Product();
            product.setWeight(0.8);
            product.setPrice(0.4);
            product.setOrder(order);

            Set<Product> products = order.getProducts();
            products.add(product);

            order.setProducts(products);
            order.setStartDate(Timestamp.valueOf(LocalDateTime.now()));
            order.setStartVertex(0);
            order.setEndVertex(1);

            order.setClient(firstClient);
            Set<Order> orders = firstClient.getOrders();
            orders.add(order);
            firstClient.setOrders(orders);

            session.save(firstClient);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            session.close();
        }
    }
}