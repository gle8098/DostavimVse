package ru.fivt.dostavimvse;

import ru.fivt.dostavimvse.models.Route;
import ru.fivt.dostavimvse.models.RouteLeg;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

/**
 * Created by akhtyamovpavel on 02.12.16.
 */
public class RouteIterator implements Iterator<RouteLeg>{
    private Route route;
    private Set<RouteLeg> routeLegSet;
//    private RouteLeg routeLeg;
    private int currentVertex;
    private int endVertex;

    public RouteIterator(Route route) {
        this.route = route;
        this.routeLegSet = route.getLegSet();
        this.currentVertex = route.getOrder().getStartVertex();
    }

    @Override
    public boolean hasNext() {
        return currentVertex != endVertex;
    }

    @Override
    public RouteLeg next() {
        if (!hasNext()) {
            throw new NoSuchElementException("Route iterator finished the end");
        }
        for (RouteLeg routeLeg: routeLegSet) {
            if (routeLeg.getLeg().getStartVertex() == currentVertex) {
                return routeLeg;
            }
        }
        throw new NoSuchElementException("Failed to found next vertex");
    }
}
