package com.backend.tokokantjil.dtos.outputs;

import com.backend.tokokantjil.enumerations.State;
import jakarta.persistence.Column;

public class ProductOutputDto {
    private Long id;
    private String name;
    private State state;
    private int amount;
    private String amountUnit;
    private double buyPrice;
    private double sellPrice;
    private boolean isForRetail;
    private String notes;

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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}