package com.sojerdev.forwarder.carriage.driver;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.sojerdev.forwarder.carriage.Carriage;
import jakarta.persistence.*;

import java.util.Date;

@Entity
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

    public Driver() {}

    public Driver(String firstName, String lastName, Carriage carriage, Date dob, String licenceNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.carriage = carriage;
        this.dob = dob;
        this.licenceNumber = licenceNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getLicenceNumber() {
        return licenceNumber;
    }

    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    public Carriage getCarriage() {
        return carriage;
    }

    public void setCarriage(Carriage carriage) {
        this.carriage = carriage;
    }
}
