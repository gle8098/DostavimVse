package ru.fivt.dostavimvse;

import org.hibernate.Session;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import ru.fivt.dostavimvse.models.Client;
import ru.fivt.dostavimvse.models.Order;

/**
 * Created by akhtyamovpavel on 06.12.16.
 */
@RestController
public class OrderController {
    @RequestMapping(value = "/create/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String createOrder(@PathVariable("id") int clientId, @RequestParam(name = "receiver") int receiverId,
                                  @RequestBody Order order) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        try {
            Client client = session.get(Client.class, clientId);
            Client receiver = session.get(Client.class, receiverId);

            if (client == null || receiver == null) {
                JSONObject object = new JSONObject();
                object.append("message", "No such client or receiver");
                object.append("code", 400);
                return object.toString();
            }
            order.setClient(client);
            order.setReceiver(receiver);
            Operator.getInstance().createRoute(order);
            JSONObject response = new JSONObject();
            response.append("message", "Route created");
            response.append("code", 200);
            return response.toString();
//            return "Route created";
        } catch (Exception e) {
            JSONObject response = new JSONObject();
            response.append("message", "Error happened");
            response.append("code", 400);
            return response.toString();
        }
    }
}
