package com.backend.tokokantjil.dtos.outputs;

import java.util.List;
import java.util.Set;

public class DishOutputDto {
    private Long id;
    private String name;
    private int servings;
    private double productionPrice;
    private double sellPrice;
    private double stock;
    private boolean isAppraised;
    private String notes;
    private List<ProductOutputDto> products;

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

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) {
        this.stock = stock;
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
}
