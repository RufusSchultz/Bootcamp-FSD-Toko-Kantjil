package com.backend.tokokantjil.models;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column
    private String street;
    @Column
    private int houseNumber;
    @Column
    private String houseNumberSuffix;
    @Column
    private String postalCode;
    @Column
    private String city;
    @Column
    private String notes;
    @OneToMany(mappedBy = "address")
    Set<Catering> caterings = new HashSet<>();
    @OneToMany(mappedBy = "address")
    Set<Customer> customers = new HashSet<>();

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(int houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getHouseNumberSuffix() {
        return houseNumberSuffix;
    }

    public void setHouseNumberSuffix(String houseNumberSuffix) {
        this.houseNumberSuffix = houseNumberSuffix;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<Catering> getCaterings() {
        return caterings;
    }

    public void setCaterings(Set<Catering> caterings) {
        this.caterings = caterings;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(Set<Customer> customers) {
        this.customers = customers;
    }
}
