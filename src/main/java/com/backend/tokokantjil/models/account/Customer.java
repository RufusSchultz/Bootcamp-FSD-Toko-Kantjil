package com.backend.tokokantjil.models.account;

import com.backend.tokokantjil.models.Invoice;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
public class Customer extends Account {
    @Column
    @OneToMany(mappedBy = "customer")
    //werkt dit goed??
    private List<Invoice> orderHistory = new ArrayList<>();

    public List<Invoice> getOrderHistory() {
        return orderHistory;
    }

    public void setOrderHistory(List<Invoice> orderHistory) {
        this.orderHistory = orderHistory;
    }
}
