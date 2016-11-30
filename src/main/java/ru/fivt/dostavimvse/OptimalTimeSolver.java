package ru.fivt.dostavimvse;

import ru.fivt.dostavimvse.models.Leg;
import ru.fivt.dostavimvse.models.Order;
import ru.fivt.dostavimvse.models.Route;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by akhtyamovpavel on 30.11.16.
 */
public class OptimalTimeSolver implements OptimalSolver {
    @Override
    public Route buildOptimalRoute(Order order) {
        int startVertex = order.getStartVertex();
        int endVertex = order.getEndVertex();


        List<Leg> legs = Graph.getInstance().getLegs();

        ArrayList<LinkedList<Leg>> legsMatrix = new ArrayList<>();
        int maxVertex = 0;
        for (Leg leg: legs) {
            int legStartVertex = leg.getStartVertex();
            int legEndVertex = leg.getEndVertex();
            if (startVertex > maxVertex) {
                maxVertex = startVertex;
            }
            if (endVertex > maxVertex) {
                maxVertex = endVertex;
            }
        }
        for (int index = 0; index <= maxVertex; ++index) {
            legsMatrix.add(new LinkedList<>());
        }

        ArrayList<Double> distances = new ArrayList<>(maxVertex + 1);
        for (int index = 0; index <= maxVertex; ++index) {
            if (index != startVertex) {
                distances.set(index, Double.MAX_VALUE);
            } else {
                distances.set(index, 0.0);
            }
        }

        // TODO: write this code in normal way
        return new Route();
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
