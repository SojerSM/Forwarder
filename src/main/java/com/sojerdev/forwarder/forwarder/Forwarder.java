package com.sojerdev.forwarder.forwarder;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sojerdev.forwarder.carriage.Carriage;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="forwarder")
public class Forwarder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="forwarder_id")
    private int forwarderId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="dob")
    private Date dob;

    @JsonManagedReference
    @OneToMany(mappedBy = "forwarder", cascade = CascadeType.ALL)
    private List<Carriage> carriages = new ArrayList<>();

    public Forwarder() {}

    public Forwarder(String firstName, String lastName, Date dob, List<Carriage> carriages) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dob = dob;
        this.carriages = carriages;
    }

    public int getForwarderId() {
        return forwarderId;
    }

    public void setForwarderId(int forwarderId) {
        this.forwarderId = forwarderId;
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

    public List<Carriage> getCarriages() {
        return carriages;
    }

    public void setCarriages(List<Carriage> carriages) {
        this.carriages = carriages;
    }
}
