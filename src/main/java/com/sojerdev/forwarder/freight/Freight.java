package com.sojerdev.forwarder.freight;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sojerdev.forwarder.carriage.Carriage;
import jakarta.persistence.*;

@Entity
@Table(name="freight")
public class Freight {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="freight_id")
    private int freightId;

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

    public Freight(int distance, String companyName, double value, Carriage carriage) {
        this.distance = distance;
        this.companyName = companyName;
        this.value = value;
        this.carriage = carriage;
    }

    public int getFreightId() {
        return freightId;
    }

    public void setFreightId(int freightId) {
        this.freightId = freightId;
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
