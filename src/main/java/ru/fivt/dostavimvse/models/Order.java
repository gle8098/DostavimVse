package ru.fivt.dostavimvse.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.*;

/**
 * Created by akhtyamovpavel on 30.11.16.
 */

@Entity
@Table(name = "ORDERS")
public class Order implements Serializable {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order", cascade = CascadeType.ALL)
    private Set<Product> products = new HashSet<>();

    @Column(name = "START_DATE", columnDefinition = "TIMESTAMP")
    private Timestamp startDate;

    @Column(name = "ORDER_TYPE", columnDefinition = "enum('TIME';'PRICE)")
    @Enumerated(EnumType.STRING)
    private OrderType orderType;

    @Min(0)
    @Column(name = "START_VERTEX", nullable = false)
    private Integer startVertex;

    @Min(0)
    @Column(name = "END_VERTEX", nullable = false)
    private Integer endVertex;

    @ManyToOne()
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "order")
    private Route route;


    public Integer getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Integer startVertex) {
        this.startVertex = startVertex;
    }

    public Integer getEndVertex() {
        return endVertex;
    }

    public void setEndVertex(Integer endVertex) {
        this.endVertex = endVertex;
    }





    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Client getClient() {
        return client;
    }

    public Integer getId() {
        return id;
    }


    public Set<Product> getProducts() {
        return this.products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public double getOverallPrice() {
        double sum = 0;
        for (Product product : products) {
            sum += product.getPrice();
        }
        return sum;
    }

    public double getOverallWeight() {
        double sum = 0;
        for (Product product : products) {
            sum += product.getWeight();
        }
        return sum;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }
}
