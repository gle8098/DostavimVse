package ru.fivt.dostavimvse.models;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by akhtyamovpavel on 29.11.16.
 */
@Entity
@Table(name = "LEG")
public class Leg implements Serializable {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "START_VERTEX")
    private Integer startVertex;

    @Column(name = "END_VERTEX")
    private Integer endVertex;

    @Column(name = "MAX_WEIGHT")
    private Double maxWeight;


    @Column(name = "BASE_PRICE")
    private Double basePrice;

    @Column(name="LEG_TYPE", columnDefinition = "enum('AVIA';'TRAIN')")
    @Enumerated(EnumType.STRING)
    private LegType legType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Double getMaxWeight() {
        return maxWeight;
    }

    public void setMaxWeight(Double maxWeight) {
        this.maxWeight = maxWeight;
    }

    public Double getSendTime() {
        return null;
    }

    public Double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Double basePrice) {
        this.basePrice = basePrice;
    }

    public LegType getLegType() {
        return legType;
    }

    public void setLegType(LegType legType) {
        this.legType = legType;
    }
}
