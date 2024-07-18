package com.backend.tokokantjil.dtos.outputs;

import com.backend.tokokantjil.models.Order;

import java.util.Set;

public class CustomerOutputDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private String notes;
    private Set<OrderOutputDto> orderOutputDtoSet;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Set<OrderOutputDto> getOrderOutputDtoSet() {
        return orderOutputDtoSet;
    }

    public void setOrderOutputDtoSet(Set<OrderOutputDto> orderOutputDtoSet) {
        this.orderOutputDtoSet = orderOutputDtoSet;
    }
}
