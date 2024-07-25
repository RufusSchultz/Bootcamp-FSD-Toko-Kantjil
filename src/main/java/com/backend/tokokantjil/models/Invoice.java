package com.backend.tokokantjil.models;

import jakarta.persistence.*;

import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    @Column
    private double finalPrice;
    @Column
    private boolean isPaid;
    @Column
    private String notes;
    @OneToOne
    private Order order;


    public Long getId() {
        return id;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double totalPrice) {
        this.finalPrice = totalPrice;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
