package ru.fivt.dostavimvse;

import ru.fivt.dostavimvse.models.Order;
import ru.fivt.dostavimvse.models.Route;

/**
 * Created by akhtyamovpavel on 02.12.16.
 */
public class OrderChangeLegTask implements Runnable {

    private Order order;

    public OrderChangeLegTask(Order order) {
        this.order = order;
    }

    @Override
    public void run() {
//        Route route = order.getRoute();
    }
}
