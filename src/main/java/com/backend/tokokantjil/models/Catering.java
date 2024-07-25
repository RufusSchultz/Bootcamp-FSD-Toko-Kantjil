package com.backend.tokokantjil.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "caterings")
public class Catering {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column(nullable = false)
    private LocalDateTime dateAndTime;
    @Column(nullable = false)
    private int numberOfPeople;
    @Column
    private double totalCostPrice;
    @Column
    private double totalSellPrice;
    @Column(nullable = false)
    private double agreedPrice;
    @Column(nullable = false)
    private boolean isAppraised;
    @Column
    private String notes;
    @ManyToMany
    @JoinTable(name = "catering_products")
    private List<Product> products = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "Catering_dishes")
    private List<Dish> dishes = new ArrayList<>();
    @ManyToOne
    private Address address;
    @OneToOne(mappedBy = "catering")
    private Order order;


    public Long getId() {
        return id;
    }

    public LocalDateTime getDateAndTime() {
        return dateAndTime;
    }

    public void setDateAndTime(LocalDateTime dateAndTime) {
        this.dateAndTime = dateAndTime;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public double getTotalCostPrice() {
        return totalCostPrice;
    }

    public void setTotalCostPrice(double costPrice) {
        this.totalCostPrice = costPrice;
    }

    public double getTotalSellPrice() {
        return totalSellPrice;
    }

    public void setTotalSellPrice(double sellPrice) {
        this.totalSellPrice = sellPrice;
    }

    public double getAgreedPrice() {
        return agreedPrice;
    }

    public void setAgreedPrice(double price) {
        this.agreedPrice = price;
    }

    public boolean isAppraised() {
        return isAppraised;
    }

    public void setAppraised(boolean appraised) {
        isAppraised = appraised;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
