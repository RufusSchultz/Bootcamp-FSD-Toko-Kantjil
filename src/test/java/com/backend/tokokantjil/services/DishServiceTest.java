package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.DishInputDto;
import com.backend.tokokantjil.dtos.outputs.DishOutputDto;
import com.backend.tokokantjil.enumerations.State;
import com.backend.tokokantjil.models.Dish;
import com.backend.tokokantjil.models.Product;
import com.backend.tokokantjil.repositories.DishRepository;
import com.backend.tokokantjil.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class DishServiceTest {
    Dish dish;
    Dish anotherDish;
    DishInputDto dishInputDto;
    Product product;
    Product anotherProduct;

    @Mock
    DishRepository dishRepository;
    @Mock
    ProductRepository productRepository;

    @InjectMocks
    DishService service;

    @BeforeEach
    void setUp() {
        product = new Product("sambal", State.packaged, 300, "gram", 1.20, 2.20, true, 10, "");
        anotherProduct = new Product("rice", State.packaged, 10, "kilo", 50, 100, false, 1, "");
        Set<Product> productSet = new HashSet<>();
        productSet.add(product);
        dish = new Dish("main", 6, 5, 10, false, 1, "", productSet, null, null);
        anotherDish = new Dish("starter", 10, 4, 12, true, 2, "", null, null, null);
        dishInputDto = new DishInputDto(
                dish.getName(),
                dish.getServings(),
                dish.getProductionPrice(),
                dish.getSellPrice(),
                dish.getStock(),
                dish.getNotes()
        );
    }

    @AfterEach
    void tearDown() {
        dish = null;
        anotherDish = null;
        dishInputDto = null;
        product = null;
    }

    @Test
    @DisplayName("Should return every dish")
    void getEveryDish() {
        List<Dish> mockList = new ArrayList<>();
        mockList.add(dish);
        mockList.add(anotherDish);
        Mockito.when(dishRepository.findAll()).thenReturn(mockList);

        List<DishOutputDto> testList = service.getEveryDish();

        assertEquals(2, testList.size());
    }

    @Test
    @DisplayName("Should return correct dish")
    void getDishById() {
        dish.setId(1L);
        Mockito.when(dishRepository.findById(anyLong())).thenReturn(Optional.of(dish));

        DishOutputDto dishOutputDto = service.getDishById(1L);

        assertEquals("main", dishOutputDto.getName());
        assertEquals(6, dishOutputDto.getServings());
        assertEquals(1, dishOutputDto.getStock());
        assertEquals(1, dishOutputDto.getProducts().size());
    }

    @Test
    @DisplayName("Should create correct dish")
    void createNewDish() {
        Mockito.when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        DishOutputDto dishOutputDto = service.createNewDish(dishInputDto);

        assertEquals("main", dishOutputDto.getName());
        assertEquals(6, dishOutputDto.getServings());
        assertEquals(1, dishOutputDto.getStock());
    }

    @Test
    @DisplayName("Should delete correct dish")
    void deleteDishById() {
        dish.setId(1L);
        Mockito.when(dishRepository.findById(1L)).thenReturn(Optional.of(dish));

        service.deleteDishById(1L);

        Mockito.verify(dishRepository).deleteById(any());
    }

    @Test
    @DisplayName("Should correctly update dish")
    void updateDishWithNewDishInputDto() {
        anotherDish.setId(1L);
        Mockito.when(dishRepository.findById(anyLong())).thenReturn(Optional.of(anotherDish));
        Mockito.when(dishRepository.save(any(Dish.class))).thenReturn(anotherDish);

        DishOutputDto dishOutputDto = service.updateDishWithNewDishInputDto(1L, dishInputDto);

        assertEquals("main", dishOutputDto.getName());
        assertEquals(6, dishOutputDto.getServings());
        assertEquals(1, dishOutputDto.getStock());
    }

    @Test
    @DisplayName("Should correctly add correct product to correct dish")
    void addProductToCollectionOfDish() {
        dish.setId(1L);
        anotherProduct.setId(1L);
        Mockito.when(dishRepository.findById(anyLong())).thenReturn(Optional.of(dish));
        Mockito.when(productRepository.findById(anyLong())).thenReturn(Optional.of(anotherProduct));
        Mockito.when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        DishOutputDto dishOutputDto = service.addProductToCollectionOfDish(1L, 1L);

        assertEquals("main", dishOutputDto.getName());
        assertEquals("rice", dishOutputDto.getProducts().get(0).getName());
        assertFalse(dishOutputDto.isAppraised());
    }

    @Test
    @DisplayName("Should correctly remove correct product from correct dish")
    void removeProductFromCollectionOfDish() {
        dish.setId(1L);
        product.setId(1L);
        Mockito.when(dishRepository.findById(anyLong())).thenReturn(Optional.of(dish));
        Mockito.when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        String response = service.removeProductFromCollectionOfDish(1L, 1L);

        assertEquals("Product with id 1 removed from dish", response);
        assertEquals(0, dish.getProducts().size());
        assertFalse(dish.isAppraised());
    }

    @Test
    @DisplayName("Should correctly alter stock of dish")
    void alterDishStock() {
        dish.setId(1L);
        Mockito.when(dishRepository.findById(anyLong())).thenReturn(Optional.of(dish));
        Mockito.when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        service.alterDishStock(1L, 10);

        assertEquals(11, dish.getStock());
    }

    @Test
    @DisplayName("Should correctly calculate prices of dish")
    void calculateDishPrices() {
        dish.setId(1L);
        Mockito.when(dishRepository.findById(anyLong())).thenReturn(Optional.of(dish));
        Mockito.when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        service.calculateDishPrices(1L, 100);

        assertEquals(101.2, dish.getProductionPrice());
        assertEquals(152.2, dish.getSellPrice());
        assertTrue(dish.isAppraised());
    }

    @Test
    @DisplayName("Should correctly set prices of dish to zero")
    void setDishPricesToZero() {
        dish.setId(1L);
        Mockito.when(dishRepository.findById(anyLong())).thenReturn(Optional.of(dish));
        Mockito.when(dishRepository.save(any(Dish.class))).thenReturn(dish);

        service.setDishPricesToZero(1L);

        assertEquals(0, dish.getProductionPrice());
        assertEquals(0, dish.getSellPrice());
        assertFalse(dish.isAppraised());
    }
}