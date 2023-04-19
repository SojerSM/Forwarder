package com.sojerdev.forwarder.carriage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sojerdev.forwarder.carriage.driver.Driver;
import com.sojerdev.forwarder.forwarder.Forwarder;
import com.sojerdev.forwarder.carriage.freight.Freight;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="carriage")
public class Carriage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="plates")
    private String plates;

    @Column(name="price_per_km")
    private double pricePerKm;

    @Column(name="adr")
    private boolean adr;

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

    public Carriage(String plates, double pricePerKm, Forwarder forwarder, boolean adr) {
        this.plates = plates;
        this.pricePerKm = pricePerKm;
        this.forwarder = forwarder;
        this.adr = adr;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public boolean isAdr() {
        return adr;
    }

    public void setAdr(boolean adr) {
        this.adr = adr;
    }

    public Forwarder getForwarder() {
        return forwarder;
    }

    public void setForwarder(Forwarder forwarder) {
        this.forwarder = forwarder;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public List<Freight> getFreights() {
        return freights;
    }

    public void setFreights(List<Freight> freights) {
        this.freights = freights;
    }
}
