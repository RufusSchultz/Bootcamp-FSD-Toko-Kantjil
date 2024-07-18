package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.DishInputDto;
import com.backend.tokokantjil.dtos.outputs.DishOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.dtos.mappers.DishMapper;
import com.backend.tokokantjil.models.Dish;
import com.backend.tokokantjil.models.Product;
import com.backend.tokokantjil.repositories.DishRepository;
import com.backend.tokokantjil.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService {
    private final DishRepository dishRepository;
    private final ProductRepository productRepository;

    public DishService(DishRepository dishRepository, ProductRepository productRepository) {
        this.dishRepository = dishRepository;
        this.productRepository = productRepository;
    }

    public List<DishOutputDto> getAllDishes() {
        List<DishOutputDto> list = new ArrayList<>();
        for (Dish i: this.dishRepository.findAll()) {
            list.add(DishMapper.fromDishToDishOutputDto(i));
        }
        return list;
    }

    public DishOutputDto getDishById(Long id) {
        Dish dish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));
        return DishMapper.fromDishToDishOutputDto(dish);
    }

    public DishOutputDto createDish(DishInputDto dishInputDto) {
        Dish dish = this.dishRepository.save(DishMapper.fromDishInputDtoToDish(dishInputDto));
        return DishMapper.fromDishToDishOutputDto(dish);
    }

    public void deleteDish(Long id) {
        if(this.dishRepository.findById(id).isPresent()) {
            this.dishRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No dish with id " + id + " found.");
        }
    }

    public DishOutputDto updateDish(Long id, DishInputDto dishInputDto) {
        Dish oldDish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));
        Dish dishUpdate = DishMapper.fromDishInputDtoToDish(dishInputDto);
        Dish newDish = this.dishRepository.save(DishMapper.fromDishToUpdatedDish(oldDish, dishUpdate));

        return DishMapper.fromDishToDishOutputDto(newDish);
    }

    public DishOutputDto addProductToDish(Long dishId, Long productId, double amountModifier) {
        Dish dish = this.dishRepository.findById(dishId).orElseThrow(() -> new RecordNotFoundException("No dish with id " + dishId + "found."));
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new RecordNotFoundException("No product with id " + dishId + "found."));

        product.setAmount(product.getAmount() * amountModifier);
        dish.getProducts().add(product);
        this.dishRepository.save(dish);

        return DishMapper.fromDishToDishOutputDto(dish);
    }
}
