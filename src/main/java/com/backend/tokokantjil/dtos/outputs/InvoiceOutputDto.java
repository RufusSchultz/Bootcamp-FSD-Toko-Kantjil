package com.backend.tokokantjil.dtos.outputs;


public class InvoiceOutputDto {
    private Long id;
    private double finalPrice;
    private boolean isPaid;
    private String notes;
    private OrderOutputDto order;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(double finalPrice) {
        this.finalPrice = finalPrice;
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

    public OrderOutputDto getOrder() {
        return order;
    }

    public void setOrder(OrderOutputDto order) {
        this.order = order;
    }
}
