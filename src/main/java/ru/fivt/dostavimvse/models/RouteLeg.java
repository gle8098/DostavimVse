package ru.fivt.dostavimvse.models;

import javax.persistence.*;

/**
 * Created by akhtyamovpavel on 30.11.16.
 */

@Entity
@Table(name = "ROUTE_LEG")
public class RouteLeg {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Leg getLeg() {
        return leg;
    }

    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    @ManyToOne()
    @JoinColumn(name = "LEG_ID", nullable = false)
    private Leg leg;

    @ManyToOne()
    @JoinColumn(name = "ROUTE_ID", nullable = false)
    private Route route;
}
