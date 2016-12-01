package ru.fivt.dostavimvse.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created by akhtyamovpavel on 30.11.16.
 */
@Entity
@Table(name = "ROUTE")
public class Route {
    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Order getOrder() {
        return order;
    }

    @ManyToOne()
    @JoinColumn(name = "ORDER_ID", nullable = false)
    private Order order;

    public void setLegSet(Set<RouteLeg> legSet) {
        this.legSet = legSet;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "route", cascade = CascadeType.ALL)
    private Set<RouteLeg> legSet = new HashSet<>();

    public Set<RouteLeg> getLegSet() {
        return legSet;
    }

    public List<RouteLeg> getLegs() {
        int startVertex = order.getStartVertex();
        int endVertex = order.getEndVertex();


        List<RouteLeg> orderedLegs = new LinkedList<>();

        int currentVertex = startVertex;

        while (currentVertex != endVertex) {
            for (RouteLeg leg: legSet) {
                if (leg.getLeg().getStartVertex() == currentVertex) {
                    orderedLegs.add(leg);
                    break;
                }
            }
        }
        return orderedLegs;
    }
}
