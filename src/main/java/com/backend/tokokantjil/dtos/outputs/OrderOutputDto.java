package com.backend.tokokantjil.dtos.outputs;

import com.backend.tokokantjil.enumerations.Status;

import java.time.LocalDateTime;
import java.util.List;

public class OrderOutputDto {
    private Long id;
    private String title;
    private LocalDateTime createdAt;
    private String status;
    private double totalPrice;
    private double totalCost;
    private boolean isCateringOrder;
    private boolean isAppraised;
    private List<ProductOutputDto> products;
    private List<DishOutputDto> dishes;
    private CateringOutputDto catering;
    private CustomerOutputDto customer;
    private String createdBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isCateringOrder() {
        return isCateringOrder;
    }

    public void setCateringOrder(boolean cateringOrder) {
        isCateringOrder = cateringOrder;
    }

    public boolean isAppraised() {
        return isAppraised;
    }

    public void setAppraised(boolean appraised) {
        isAppraised = appraised;
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

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public CateringOutputDto getCatering() {
        return catering;
    }

    public void setCatering(CateringOutputDto catering) {
        this.catering = catering;
    }

    public CustomerOutputDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerOutputDto customer) {
        this.customer = customer;
    }
}
