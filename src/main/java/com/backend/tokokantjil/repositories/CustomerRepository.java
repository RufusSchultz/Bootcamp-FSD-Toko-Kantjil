package com.backend.tokokantjil.repositories;

import com.backend.tokokantjil.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
