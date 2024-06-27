package com.backend.tokokantjil.models;

import com.backend.tokokantjil.models.account.Customer;
import jakarta.persistence.*;

@Entity
@Table(name = "invoices")
public class Invoice {
    @Id
    @GeneratedValue
    private Long id;
    @Column
    private boolean isPaid;
    @Column
    private String notes;

    @ManyToOne()
    @JoinColumn(nullable = false)
    private Customer customer;

    public Long getId() {
        return id;
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
}
