package com.sojerdev.forwarder.forwarder;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sojerdev.forwarder.carriage.Carriage;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name="forwarder")
public class Forwarder {

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

    @JsonManagedReference
    @OneToMany(mappedBy = "forwarder", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Carriage> carriages = new ArrayList<>();
}
