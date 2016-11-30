package ru.fivt.dostavimvse;

import ru.fivt.dostavimvse.models.Leg;
import ru.fivt.dostavimvse.models.Order;

/**
 * Created by akhtyamovpavel on 30.11.16.
 */
public interface OptimalSolver {

    void buildOptimalRoute(Order order);

    double getLegCost(Order order, Leg leg);
}
