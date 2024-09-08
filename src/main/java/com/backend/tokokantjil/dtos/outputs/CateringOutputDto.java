package com.backend.tokokantjil.dtos.outputs;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public class CateringOutputDto {
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dateAndTime;
    private int numberOfPeople;
    private double totalCostPrice;
    private double totalSellPrice;
    private double agreedPrice;
    private boolean isAppraised;
    private String notes;
    private List<ProductOutputDto> products;
    private List<DishOutputDto> dishes;
    private AddressOutputDto address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setTotalCostPrice(double totalCostPrice) {
        this.totalCostPrice = totalCostPrice;
    }

    public double getTotalSellPrice() {
        return totalSellPrice;
    }

    public void setTotalSellPrice(double totalSellPrice) {
        this.totalSellPrice = totalSellPrice;
    }

    public double getAgreedPrice() {
        return agreedPrice;
    }

    public void setAgreedPrice(double agreedPrice) {
        this.agreedPrice = agreedPrice;
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

    public List<ProductOutputDto> getProducts() {
        return products;
    }

    public void setProducts(List<ProductOutputDto> products) {
        this.products = products;
    }

    public List<DishOutputDto> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishOutputDto> dishes) {
        this.dishes = dishes;
    }

    public AddressOutputDto getAddress() {
        return address;
    }

    public void setAddress(AddressOutputDto address) {
        this.address = address;
    }
}
