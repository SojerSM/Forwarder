package com.sojerdev.forwarder.carriage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sojerdev.forwarder.driver.Driver;
import com.sojerdev.forwarder.forwarder.Forwarder;
import com.sojerdev.forwarder.freight.Freight;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="carriage")
public class Carriage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="carriage_id")
    private int carriageId;

    @Column(name="plates")
    private String plates;

    @Column(name="price_per_km")
    private double pricePerKm;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="forwarder_id")
    private Forwarder forwarder;

    @JsonManagedReference
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "carriage")
    private Driver driver;

    @JsonManagedReference
    @OneToMany(mappedBy = "carriage", cascade = CascadeType.ALL)
    private List<Freight> freights;

    public Carriage() {}

    public Carriage(String plates, double pricePerKm, Forwarder forwarder) {
        this.plates = plates;
        this.pricePerKm = pricePerKm;
        this.forwarder = forwarder;
    }

    public int getCarriageId() {
        return carriageId;
    }

    public void setCarriageId(int carriageId) {
        this.carriageId = carriageId;
    }

    public String getPlates() {
        return plates;
    }

    public void setPlates(String plates) {
        this.plates = plates;
    }

    public double getPricePerKm() {
        return pricePerKm;
    }

    public void setPricePerKm(double pricePerKm) {
        this.pricePerKm = pricePerKm;
    }

    public Forwarder getForwarder() {
        return forwarder;
    }

    public void setForwarder(Forwarder forwarder) {
        this.forwarder = forwarder;
    }
}
