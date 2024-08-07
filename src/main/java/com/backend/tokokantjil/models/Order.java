package com.backend.tokokantjil.models;

import com.backend.tokokantjil.enumerations.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column
    private String title;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createdAt;
    @Column(nullable = false)
    private Status status;
    @Column
    private double totalPrice;
    @Column
    private double totalCost;
    @Column(nullable = false)
    private boolean isCateringOrder;
    @Column
    private boolean isAppraised;
    @ManyToMany
    @JoinTable(name = "order_products")
    private List<Product> products = new ArrayList<>();
    @ManyToMany
    @JoinTable(name = "order_dishes")
    private List<Dish> dishes = new ArrayList<>();
    @OneToOne
    private Catering catering;
    @ManyToOne
    private Customer customer;
    @OneToOne(mappedBy = "order")
    private Invoice invoice;
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User user;

    public Long getId() {
        return id;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Catering getCatering() {
        return catering;
    }

    public void setCatering(Catering catering) {
        this.catering = catering;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }
}
