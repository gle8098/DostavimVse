package ru.fivt.dostavimvse;

import org.hibernate.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.fivt.dostavimvse.models.Client;

/**
 * Created by akhtyamovpavel on 30.11.16.
 */
@Controller
public class MainController {


    @RequestMapping(value="/orders", method = RequestMethod.GET)
    public String getOrderHistory(Model model) {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Client client = session.get(Client.class, 5);

        model.addAttribute("client", client);

        session.close();
        return "getorderhistory";
    }

    @RequestMapping(value="/legs", method = RequestMethod.GET)
    public String getLegs(Model model) {
        model.addAttribute("legs", Graph.getInstance().getLegs());

        return "getlegs";
    }

}
