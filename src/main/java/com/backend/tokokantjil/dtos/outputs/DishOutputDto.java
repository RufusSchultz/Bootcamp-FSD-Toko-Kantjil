package com.backend.tokokantjil.dtos.outputs;


import java.util.List;

public class DishOutputDto {
    private Long id;
    private String name;
    private int servings;
    private double productionPrice;
    private double sellPrice;
    private String notes;
    private List<ProductOutputDto> productOutputDtoList;

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

    public int getServings() {
        return servings;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    public double getProductionPrice() {
        return productionPrice;
    }

    public void setProductionPrice(double productionPrice) {
        this.productionPrice = productionPrice;
    }

    public double getSellPrice() {
        return sellPrice;
    }

    public void setSellPrice(double sellPrice) {
        this.sellPrice = sellPrice;
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
}
