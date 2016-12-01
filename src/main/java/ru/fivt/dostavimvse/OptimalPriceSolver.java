package ru.fivt.dostavimvse;

import ru.fivt.dostavimvse.models.Leg;
import ru.fivt.dostavimvse.models.Order;
import ru.fivt.dostavimvse.models.Route;

/**
 * Created by akhtyamovpavel on 02.12.16.
 */
public class OptimalPriceSolver implements OptimalSolver {
    @Override
    public Route buildOptimalRoute(Order order) {
        return null;
    }

    @Override
    public double getLegCost(Order order, Leg leg) {
        return order.getOverallPrice() + leg.getBasePrice();
    }
}
