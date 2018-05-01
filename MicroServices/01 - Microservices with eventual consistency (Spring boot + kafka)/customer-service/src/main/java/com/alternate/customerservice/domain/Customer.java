package com.alternate.customerservice.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerId;

    private String name;

    private Double creditLimit;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "customerId", cascade = CascadeType.ALL)
    private Collection<CreditReservation> creditReservations;

    public Customer() {
    }

    public Customer(String name, Double creditLimit) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.creditReservations = new ArrayList<>();
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Collection<CreditReservation> getCreditReservations() {
        return creditReservations;
    }

    public void addCreditReservation(CreditReservation creditReservation) {
        this.creditReservations.add(creditReservation);
    }
}
