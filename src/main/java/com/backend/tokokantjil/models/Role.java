package com.backend.tokokantjil.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String rolename) {
        this.roleName = rolename;
    }
}
