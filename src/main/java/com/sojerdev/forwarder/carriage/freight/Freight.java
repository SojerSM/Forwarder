package com.sojerdev.forwarder.carriage.freight;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sojerdev.forwarder.carriage.Carriage;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
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

    @Column(name="adr")
    private boolean adr;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="carriage_id")
    private Carriage carriage;
}
