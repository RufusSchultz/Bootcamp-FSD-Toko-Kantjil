package com.backend.tokokantjil.repositories;

import com.backend.tokokantjil.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
