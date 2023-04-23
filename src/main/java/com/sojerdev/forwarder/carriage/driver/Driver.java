package com.sojerdev.forwarder.carriage.driver;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sojerdev.forwarder.carriage.Carriage;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name="driver")
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="dob")
    private Date dob;

    @Column(name="licence_number")
    private String licenceNumber;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="carriage_id")
    private Carriage carriage;
}
