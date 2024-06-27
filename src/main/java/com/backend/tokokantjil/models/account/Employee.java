package com.backend.tokokantjil.models.account;

import com.backend.tokokantjil.enumerations.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;

@Entity
public class Employee extends Account{
    @Column(nullable = false)
    private Role role;
    @Column(nullable = false)
    private double salary;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
