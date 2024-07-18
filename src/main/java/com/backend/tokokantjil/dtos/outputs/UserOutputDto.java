package com.backend.tokokantjil.dtos.outputs;

import com.backend.tokokantjil.models.Role;

import java.util.Set;

public class UserOutputDto {
    private String username;
    private String password;
    private Set<Role> roles;
    private String firstName;
    private String lastName;
    private String email;
    private Long phoneNumber;
    private double salary;
    private String notes;
    private Set<OrderOutputDto> orderOutputDtoSet;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
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
