package ru.fivt.dostavimvse;

import ru.fivt.dostavimvse.models.Leg;
import ru.fivt.dostavimvse.models.Order;

/**
 * Created by akhtyamovpavel on 30.11.16.
 */
public class OptimalTimeSolver implements OptimalSolver {
    @Override
    public void buildOptimalRoute(Order order) {

    }

    @Override
    public double getLegCost(Order order, Leg leg) {
        double weight = order.getOverallWeight();
        if (weight > leg.getMaxWeight()) {
            return Double.MAX_VALUE;
        }
        return leg.getSendTime(); // stub
    }
}
