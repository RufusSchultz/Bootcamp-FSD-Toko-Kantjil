package com.backend.tokokantjil.dtos.outputs;


import com.backend.tokokantjil.models.Customer;

public class InvoiceOutputDto {
    private Long id;
    private double totalPrice;
    private boolean isPaid;
    private String notes;
    private OrderOutputDto orderOutputDto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
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

    public OrderOutputDto getOrderOutputDto() {
        return orderOutputDto;
    }

    public void setOrderOutputDto(OrderOutputDto orderOutputDto) {
        this.orderOutputDto = orderOutputDto;
    }
}
