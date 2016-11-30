package ru.fivt.dostavimvse;

import org.hibernate.Query;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by akhtyamovpavel on 30.11.16.
 */
public class Graph {
    private static Graph ourInstance = new Graph();

    public static Graph getInstance() {
        return ourInstance;
    }

    private Graph() {
    }

    public List getLegs() {
        Session session = HibernateSessionFactory.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Leg");
        return query.list();
    }
}
