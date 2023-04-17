package com.sojerdev.forwarder.carriage.freight;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sojerdev.forwarder.carriage.Carriage;
import jakarta.persistence.*;

@Entity
@Table(name="freight")
public class Freight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="order_number")
    private String orderNumber;

    @Column(name="distance")
    private int distance;

    @Column(name="company_name")
    private String companyName;

    @Column(name="value")
    private double value;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="carriage_id")
    private Carriage carriage;

    public Freight() {}

    public Freight(String orderNumber, int distance, String companyName, double value, Carriage carriage) {
        this.orderNumber = orderNumber;
        this.distance = distance;
        this.companyName = companyName;
        this.value = value;
        this.carriage = carriage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Carriage getCarriage() {
        return carriage;
    }

    public void setCarriage(Carriage carriage) {
        this.carriage = carriage;
    }
}
