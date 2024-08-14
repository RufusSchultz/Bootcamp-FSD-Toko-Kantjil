package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.enumerations.State;
import com.backend.tokokantjil.models.Dish;
import com.backend.tokokantjil.models.Product;
import com.backend.tokokantjil.repositories.DishRepository;
import com.backend.tokokantjil.repositories.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class DishControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    DishRepository dishRepository;
    @Autowired
    ProductRepository productRepository;

    Dish dish;
    Dish anotherDish;
    Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("sambal", State.packaged, 300, "gram", 1.20, 2.20, true, 10, "");
        Set<Product> productSet = new HashSet<>();
        productSet.add(product);
        dish = new Dish("main", 6, 5, 10, false, 1, "", productSet, null, null);
        anotherDish = new Dish("starter", 10, 4, 12, true, 2, "", null, null, null);
        productRepository.save(product);
        dishRepository.save(dish);
        dishRepository.save(anotherDish);
    }

    @AfterEach
    public void tearDown() {
        dishRepository.deleteAll();
        productRepository.deleteAll();
        dish = null;
        anotherDish = null;
        product = null;
    }

    @Test
    @DisplayName("Should correctly return all dishes")
    void getAllDishes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/dishes"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("main")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is("starter")));
    }

    @Test
    @DisplayName("Should return correct dish")
    void getDish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/dishes/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("main")));
    }

    @Test
    @DisplayName("Should create correct dish")
    void createDish() throws Exception {
        String requestJson = """
                {
                     "name" : "Nasi Goreng",
                     "servings" : 4,
                     "productionPrice" : 3,
                     "sellPrice" : 5.49,
                     "stock" : 2,
                     "notes" : ""
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/dishes")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Nasi Goreng")));
    }

    @Test
    @DisplayName("Should delete correct dish")
    void deleteDish() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/dishes/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("Should correctly update dish")
    void updateDish() throws Exception {
        String requestJson = """
                {
                     "name" : "Nasi Goreng",
                     "servings" : 4,
                     "productionPrice" : 3,
                     "sellPrice" : 5.49,
                     "stock" : 2,
                     "notes" : ""
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put("/dishes/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("Nasi Goreng")));
    }

    @Test
    @DisplayName("Should correctly add product to dish")
    void addProductToDish() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/dishes/{id}/products?productId={productId}&amountMultiplier={amountMultiplier}", 1, 1, 0.5))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Added product 1 to dish 1 with a multiplier of 0.5.", response);
    }

    @Test
    @DisplayName("Should correctly remove product from dish")
    void removeProductFromDish() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/dishes/{id}/products?productId={productId}", 1, 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Product with id 1 removed from dish", response);
    }

    @Test
    @DisplayName("Should correctly increase stock of dish")
    void stockDish() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/dishes/{id}/increase-stock?amount={amount}", 1, 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Stock increased to 2.0.", response);
    }

    @Test
    @DisplayName("Should correctly decrease stock of dish")
    void consumeDish() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/dishes/{id}/decrease-stock?amount={amount}", 1, 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Stock decreased to 0.0.", response);
    }

    @Test
    @DisplayName("Should set correct prices for dish")
    void setDishPrices() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/dishes/{id}/prices?laborCost={laborCost}", 1, 10))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Dish prices calculated. Cost price: 11.2 and sell price: 17.2", response);
    }

    @Test
    @DisplayName("Should set prices of dish to zero")
    void resetPrices() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/dishes/{id}/prices/reset", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productionPrice", is(0.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sellPrice", is(0.0)));
    }
}