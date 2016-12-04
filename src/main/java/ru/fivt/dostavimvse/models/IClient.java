package ru.fivt.dostavimvse.models;

import java.util.Set;

/**
 * Created by akhtyamovpavel on 04.12.16.
 */
public interface IClient {

    Set<Order> getOrders();

    void requestCreateOrder(Order order);

    void receiveOrder(Order order);

    Order getOrderById(Integer id);
}
