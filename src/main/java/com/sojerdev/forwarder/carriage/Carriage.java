package com.sojerdev.forwarder.carriage;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sojerdev.forwarder.carriage.driver.Driver;
import com.sojerdev.forwarder.forwarder.Forwarder;
import com.sojerdev.forwarder.carriage.freight.Freight;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
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
}
