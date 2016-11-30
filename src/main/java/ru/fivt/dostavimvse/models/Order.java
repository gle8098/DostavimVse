package ru.fivt.dostavimvse.models;

import javax.persistence.*;
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
//    @Temporal(TemporalType.TIMESTAMP)
    private Timestamp startDate;

    @ManyToOne()
    @JoinColumn(name = "CLIENT_ID", nullable = false)
    private Client client;


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
}
