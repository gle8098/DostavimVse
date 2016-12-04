package ru.fivt.dostavimvse;

import ch.qos.logback.core.db.dialect.MySQLDialect;
import org.hibernate.Session;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.fivt.dostavimvse.models.Client;

/**
 * Created by akhtyamovpavel on 30.11.16.
 */
@Controller
public class MainController {


    @RequestMapping(value="/orders/{id}", method = RequestMethod.GET)
    public ModelAndView getOrderHistory(@PathVariable("id") int clientId, Model model) {
        ModelAndView modelAndView = new ModelAndView("getorderhistory");
        Session session = HibernateSessionFactory.getSessionFactory().openSession();

        Client client = session.get(Client.class, clientId);

        modelAndView.addObject("client", client);

        session.close();
        return modelAndView;
    }

    @RequestMapping(value="/", method=RequestMethod.GET)
    public ModelAndView getMainPage() {
        return new ModelAndView("main");
    }

    @RequestMapping(value="/legs", method = RequestMethod.GET)
    public String getLegs(Model model) {
        model.addAttribute("legs", Graph.getInstance().getLegs());

        return "getlegs";
    }

}
