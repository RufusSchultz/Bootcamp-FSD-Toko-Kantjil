package com.backend.tokokantjil.dtos.outputs;

import com.backend.tokokantjil.models.Role;
import com.backend.tokokantjil.models.UserPhoto;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Set;

public class UserOutputDto {
    private String username;
    private Set<Role> roles;
    private String firstName;
    private String lastName;
    private String email;
    private long phoneNumber;
    private double salary;
    private String notes;
    @JsonIgnoreProperties(value = {"contents","contentType"} )
    private UserPhoto userPhoto;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public UserPhoto getUserPhoto() {
        return userPhoto;
    }

    public void setUserPhoto(UserPhoto userPhoto) {
        this.userPhoto = userPhoto;
    }
}
