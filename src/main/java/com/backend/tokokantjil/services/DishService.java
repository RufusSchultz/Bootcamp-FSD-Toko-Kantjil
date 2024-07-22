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

    public List<DishOutputDto> getEveryDish() {
        List<DishOutputDto> list = new ArrayList<>();
        for (Dish i : this.dishRepository.findAll()) {
            list.add(DishMapper.fromDishToDishOutputDto(i));
        }
        return list;
    }

    public DishOutputDto getDishById(Long id) {
        Dish dish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));
        return DishMapper.fromDishToDishOutputDto(dish);
    }

    public DishOutputDto createNewDish(DishInputDto dishInputDto) {
        Dish dish = this.dishRepository.save(DishMapper.fromDishInputDtoToDish(dishInputDto));
        return DishMapper.fromDishToDishOutputDto(dish);
    }

    public void deleteDishById(Long id) {
        if (this.dishRepository.findById(id).isPresent()) {
            this.dishRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No dish with id " + id + " found.");
        }
    }

    public DishOutputDto updateDishWithNewDishInputDto(Long id, DishInputDto dishInputDto) {
        Dish oldDish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));
        Dish dishUpdate = DishMapper.fromDishInputDtoToDish(dishInputDto);
        Dish newDish = this.dishRepository.save(DishMapper.fromDishToUpdatedDish(oldDish, dishUpdate));

        return DishMapper.fromDishToDishOutputDto(newDish);
    }

    public DishOutputDto addProductToCollectionOfDish(Long dishId, Long productId, double amountMultiplier) {
        Dish dish = this.dishRepository.findById(dishId).orElseThrow(() -> new RecordNotFoundException("No dish with id " + dishId + " found."));
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new RecordNotFoundException("No product with id " + productId + " found."));

//        if (product.getStock() - amountMultiplier >= 0) {
//
//            product.setStock(product.getStock() - amountMultiplier);
//
//            dish.setProductionPrice(dish.getProductionPrice() + product.getBuyPrice() * amountMultiplier);
//            dish.setSellPrice(dish.getSellPrice() + product.getSellPrice() * amountMultiplier);
//            dish.getProducts().add(product);
//
//            this.productRepository.save(product);
//            this.dishRepository.save(dish);
//            return (DishMapper.fromDishToDishOutputDto(dish));
//        } else {
//            throw new NotEnoughInStockException("Not enough of product " + productId + " in stock!");
//        }

        dish.setProductionPrice(dish.getProductionPrice() + product.getBuyPrice() * amountMultiplier);
        dish.setSellPrice(dish.getSellPrice() + product.getSellPrice() * amountMultiplier);
        dish.getProducts().add(product);
        dish.setAppraised(false);
        this.dishRepository.save(dish);
        return (DishMapper.fromDishToDishOutputDto(dish));

    }

    public DishOutputDto increaseDishStock(Long id, int amount) {
        Dish dish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));

        dish.setStock(dish.getStock() + amount);
        this.dishRepository.save(dish);

        return DishMapper.fromDishToDishOutputDto(dish);
    }

    public DishOutputDto decreaseDishStock(Long id, int amount) {
        Dish dish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));

        dish.setStock(dish.getStock() - amount);
        this.dishRepository.save(dish);

        return DishMapper.fromDishToDishOutputDto(dish);
    }

    public String calculatePrices(Long id, double laborCost) {
        Dish dish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));

        if (!dish.isAppraised()) {
            double combinedCostPrice = 0;
            double combinedSellPrice = 0;

            for (Product product : dish.getProducts()) {
                combinedCostPrice += product.getBuyPrice();
                combinedSellPrice += product.getSellPrice();
            }

            dish.setProductionPrice(laborCost + combinedCostPrice);
            dish.setSellPrice(laborCost * 1.5 + combinedSellPrice);
            dish.setAppraised(true);
            this.dishRepository.save(dish);

            return "Dish prices calculated. Cost price: " + dish.getProductionPrice() + " and sell price: " + dish.getSellPrice();
        } else {
            return "Dish prices are already calculated. Reset prices first if you want to recalculate them.";
        }
    }

    public DishOutputDto setPricesToZero(Long id) {
        Dish dish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));

        dish.setAppraised(false);
        dish.setSellPrice(0);
        dish.setProductionPrice(0);
        this.dishRepository.save(dish);

        return DishMapper.fromDishToDishOutputDto(dish);
    }
}
