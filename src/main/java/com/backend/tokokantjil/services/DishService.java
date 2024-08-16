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
import java.util.Set;

import static com.backend.tokokantjil.constants.Constants.laborPriceMultiplier;

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

    public DishOutputDto addProductToCollectionOfDish(Long id, Long productId, double amountMultiplier) {
        Dish dish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new RecordNotFoundException("No product with id " + productId + " found."));

        dish.setProductionPrice(dish.getProductionPrice() + product.getBuyPrice() * amountMultiplier);
        dish.setSellPrice(dish.getSellPrice() + product.getSellPrice() * amountMultiplier);
        dish.getProducts().add(product);
        dish.setAppraised(false);
        this.dishRepository.save(dish);
        return (DishMapper.fromDishToDishOutputDto(dish));
    }

    public String removeProductFromCollectionOfDish(Long id, Long productId) {
        Dish dish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));
        Product baseProduct = this.productRepository.findById(productId).orElseThrow(() -> new RecordNotFoundException("No product with id " + productId + " found."));
        Set<Product> productSet = dish.getProducts();

        String response = "No product with id " + productId + " found. Dish is unchanged.";

        for (Product product : dish.getProducts()) {
            if (product.getId().equals(productId)) {
                double amountMultiplier = baseProduct.getBuyPrice() / product.getBuyPrice();

                dish.setProductionPrice(dish.getProductionPrice() - product.getBuyPrice() / amountMultiplier);
                dish.setSellPrice(dish.getSellPrice() - product.getSellPrice() / amountMultiplier);
                productSet.remove(product);
                dish.setAppraised(false);

                response = "Product with id " + productId + " removed from dish";
            }
        }
        dish.setProducts(productSet);
        this.dishRepository.save(dish);
        return response;
    }

    public String increaseDishStock(Long id, int amount) {
        Dish dish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));

        dish.setStock(dish.getStock() + amount);
        this.dishRepository.save(dish);

        String response = "Stock increased to " + dish.getStock() + ".";
        if (dish.getStock() < 0) {
            response = "Stock is still less than zero! " + response;
        }
        return response;
    }

    public String decreaseDishStock(Long id, int amount) {
        Dish dish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));

        dish.setStock(dish.getStock() - amount);
        this.dishRepository.save(dish);

        String response = "Stock decreased to " + dish.getStock() + ".";
        if (dish.getStock() < 0) {
            response = "Stock is now less than zero! " + response;
        }
        return response;
    }

    public String calculateDishPrices(Long id, double laborCost) {
        Dish dish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));

        if (!dish.isAppraised()) {
            double combinedCostPrice = 0;
            double combinedSellPrice = 0;

            for (Product product : dish.getProducts()) {
                combinedCostPrice += product.getBuyPrice();
                combinedSellPrice += product.getSellPrice();
            }

            dish.setProductionPrice(laborCost + combinedCostPrice);
            dish.setSellPrice(laborCost * laborPriceMultiplier + combinedSellPrice);
            dish.setAppraised(true);
            this.dishRepository.save(dish);

            return "Dish prices calculated. Cost price: " + dish.getProductionPrice() + " and sell price: " + dish.getSellPrice();
        } else {
            return "Dish prices are already calculated. Reset prices first if you want to recalculate them.";
        }
    }

    public DishOutputDto setDishPricesToZero(Long id) {
        Dish dish = this.dishRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No dish with id " + id + " found."));

        dish.setAppraised(false);
        dish.setSellPrice(0);
        dish.setProductionPrice(0);
        this.dishRepository.save(dish);

        return DishMapper.fromDishToDishOutputDto(dish);
    }
}
