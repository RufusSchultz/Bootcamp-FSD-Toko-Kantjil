package com.backend.tokokantjil.models;

import jakarta.persistence.*;

import java.time.LocalDate;
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
    @Column
    private LocalDateTime dateAndTime;
    @Column
    private int numberOfPeople;
    @Column
    private double price;
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
    @OneToOne
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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
