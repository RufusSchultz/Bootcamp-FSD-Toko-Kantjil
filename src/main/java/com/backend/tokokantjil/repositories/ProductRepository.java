package com.backend.tokokantjil.repositories;

import com.backend.tokokantjil.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
