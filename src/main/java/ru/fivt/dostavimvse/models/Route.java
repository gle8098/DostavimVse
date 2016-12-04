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

    @OneToOne()
    @JoinColumn(name = "ORDER_ID", nullable = false, referencedColumnName = "ID")
    private Order order;

    public void setLegs(Set<RouteLeg> legSet) {
        this.legs = legSet;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "route", cascade = CascadeType.ALL)
    private Set<RouteLeg> legs = new HashSet<>();

    public Set<RouteLeg> getLegs() {
        return legs;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
