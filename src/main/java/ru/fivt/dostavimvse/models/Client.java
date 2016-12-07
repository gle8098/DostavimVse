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
public class Client implements Serializable {

    @Id
    @Column(name = "ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    public Set<Order> getReceiverOrders() {
        return receiverOrders;
    }

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "receiver", cascade = CascadeType.ALL)
    private Set<Order> receiverOrders = new HashSet<>();


    public Set<Order> getOrders() {
        return this.orders;
    }

    public Order receiveOrder(Order order) {
        if (order.getReceiver().getId().equals(getId())) {
            order.setOrderStatus(OrderStatus.COMPLETED);
            return order;
        } else {
            return null;
        }
    }

    public Order getOrder(Integer id) {
        return orders.stream().filter((Order order) ->
            order.getId().equals(id)
        ).findFirst().get();
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Integer getId() {
        return id;
    }
}
