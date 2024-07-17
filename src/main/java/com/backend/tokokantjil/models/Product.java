package com.backend.tokokantjil.models;

import com.backend.tokokantjil.enumerations.State;
import jakarta.persistence.*;

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
    @Column
    private String notes;

    public Long getId() {
        return id;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
