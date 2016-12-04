package ru.fivt.dostavimvse.models;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by akhtyamovpavel on 29.11.16.
 */
@Entity
@Table(name = "CLIENT")
public class Client implements Serializable, IClient {



    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();


    public Set<Order> getOrders() {
        return this.orders;
    }

    @Override
    public void requestCreateOrder(Order order) {

    }

    @Override
    public void receiveOrder(Order order) {

    }

    @Override
    public Order getOrderById(Integer id) {
        return null;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }
}
