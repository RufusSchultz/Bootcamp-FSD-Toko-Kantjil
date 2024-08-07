package com.backend.tokokantjil.models;

import com.backend.tokokantjil.enumerations.State;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column
    private String name;
    @Column(nullable = false)
    private State state;
    @Column(nullable = false)
    private int amount;
    @Column(nullable = false)
    private String amountUnit;
    @Column(nullable = false)
    private double buyPrice;
    @Column(nullable = false)
    private double sellPrice;
    @Column(nullable = false)
    private boolean isForRetail;
    @Column(nullable = false)
    private double stock;
    @Column
    private String notes;
    @ManyToMany(mappedBy = "products")
    private List<Order> orders = new ArrayList<>();
    @ManyToMany(mappedBy = "products")
    private Set<Dish> dishes = new HashSet<>();
    @ManyToMany(mappedBy = "products")
    private List<Catering> caterings = new ArrayList<>();

    public Product(){}

    public Product(String name, State state, int amount, String amountUnit, double buyPrice, double sellPrice, boolean isForRetail, double stock, String notes) {
        this.name = name;
        this.state = state;
        this.amount = amount;
        this.amountUnit = amountUnit;
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
        this.isForRetail = isForRetail;
        this.stock = stock;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getAmountUnit() {
        return amountUnit;
    }

    public void setAmountUnit(String amountUnit) {
        this.amountUnit = amountUnit;
    }

    public double getBuyPrice() {
        return buyPrice;
    }

    public void setBuyPrice(double buyPrice) {
        this.buyPrice = buyPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
    }

    public boolean isForRetail() {
        return isForRetail;
    }

    public void setForRetail(boolean forRetail) {
        isForRetail = forRetail;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Set<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(Set<Dish> dishes) {
        this.dishes = dishes;
    }

    public List<Catering> getCaterings() {
        return caterings;
    }

    public void setCaterings(List<Catering> caterings) {
        this.caterings = caterings;
    }
}
