package com.backend.tokokantjil.dtos.outputs;

import java.time.LocalDateTime;
import java.util.List;

public class CateringOutputDto {
    private Long id;
    private LocalDateTime dateAndTime;
    private int numberOfPeople;
    private double price;
    private String notes;
    private List<ProductOutputDto> productOutputDtoList;
    private List<DishOutputDto> dishOutputDtoList;

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

    public List<ProductOutputDto> getProductOutputDtoList() {
        return productOutputDtoList;
    }

    public void setProductOutputDtoList(List<ProductOutputDto> productOutputDtoList) {
        this.productOutputDtoList = productOutputDtoList;
    }

    public List<DishOutputDto> getDishOutputDtoList() {
        return dishOutputDtoList;
    }

    public void setDishOutputDtoList(List<DishOutputDto> dishOutputDtoList) {
        this.dishOutputDtoList = dishOutputDtoList;
    }
}
