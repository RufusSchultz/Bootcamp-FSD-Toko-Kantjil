package com.backend.tokokantjil.repositories;

import com.backend.tokokantjil.models.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository extends JpaRepository<Dish, Long> {
}
