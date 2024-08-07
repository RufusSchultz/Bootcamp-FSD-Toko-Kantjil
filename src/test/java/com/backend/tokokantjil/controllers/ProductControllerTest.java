package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.enumerations.State;
import com.backend.tokokantjil.models.Product;
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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class ProductControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ProductRepository productRepository;

    Product product;
    Product anotherProduct;

    @BeforeEach
    public void setUp() {
        product = new Product("sambal", State.packaged, 300, "gram", 1.20, 2.20, true, 10, "");
        anotherProduct = new Product("rice", State.packaged, 10, "kilo", 50, 100, false, 1, "");
        productRepository.save(product);
        productRepository.save(anotherProduct);
    }

    @AfterEach
    public void tearDown() {
        productRepository.deleteAll();
        product = null;
        anotherProduct = null;
    }

    @Test
    @DisplayName("Should correctly return all products")
    void getAllProducts() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", is("sambal")))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].name", is("rice")));
    }

    @Test
    @DisplayName("Should return correct product")
    void getProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/products/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("sambal")));
    }

    @Test
    @DisplayName("Should create correct product")
    void createProduct() throws Exception {
        String requestJson = """
                {
                        "name" : "lemon",
                        "state" : "fresh",
                        "amount" : 1,
                        "amountUnit" : "piece",
                        "buyPrice" : 0.1,
                        "sellPrice" : 0.5,
                        "isForRetail" : true,
                        "stock" : 25,
                        "notes" : ""
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/products")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("lemon")));
    }

    @Test
    @DisplayName("Should delete correct order")
    void deleteProduct() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/products/1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    @DisplayName("Should correctly update product")
    void updateProduct() throws Exception {
        String requestJson = """
                {
                        "name" : "lemon",
                        "state" : "fresh",
                        "amount" : 1,
                        "amountUnit" : "piece",
                        "buyPrice" : 0.1,
                        "sellPrice" : 0.5,
                        "isForRetail" : true,
                        "stock" : 25,
                        "notes" : ""
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put("/products/1")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", is("lemon")));
    }

    @Test
    @DisplayName("Should correctly increase stock of product")
    void stockProduct() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/products/1/increase-stock?amount=1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Stock increased to 11.0.", response);
    }

    @Test
    @DisplayName("Should correctly decrease stock of product")
    void consumeProduct() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/products/1/decrease-stock?amount=1"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Stock decreased to 9.0.", response);
    }
}