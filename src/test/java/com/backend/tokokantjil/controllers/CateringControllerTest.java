package com.backend.tokokantjil.controllers;

import com.backend.tokokantjil.enumerations.State;
import com.backend.tokokantjil.models.Address;
import com.backend.tokokantjil.models.Catering;
import com.backend.tokokantjil.models.Dish;
import com.backend.tokokantjil.models.Product;
import com.backend.tokokantjil.repositories.AddressRepository;
import com.backend.tokokantjil.repositories.CateringRepository;
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class CateringControllerTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    CateringRepository cateringRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    DishRepository dishRepository;
    @Autowired
    ProductRepository productRepository;

    Catering catering;
    Catering anotherCatering;
    Address address;
    Dish dish;
    Product product;

    @BeforeEach
    public void setUp() {
        product = new Product("sambal", State.packaged, 300, "gram", 1.20, 2.20, true, 10, "");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        dish = new Dish("main", 6, 5, 10, true, 1, "", null, null, null);
        List<Dish> dishList = new ArrayList<>();
        dishList.add(dish);
        address = new Address("Party House", "Feeststraat", 1, null, "1234AB", "Feeststad", "", null, null);
        catering = new Catering(LocalDateTime.of(2024, 12, 5, 15, 30), 10, 0, 0, 7500, false, "", productList, dishList, address, null);
        anotherCatering = new Catering(LocalDateTime.of(2024, 11, 5, 15, 30), 100, 0, 0, 5000, true, "", null, null, null, null);
        productRepository.save(product);
        dishRepository.save(dish);
        addressRepository.save(address);
        cateringRepository.save(catering);
        cateringRepository.save(anotherCatering);
    }

    @AfterEach
    public void tearDown() {
        cateringRepository.deleteAll();
        addressRepository.deleteAll();
        dishRepository.deleteAll();
        productRepository.deleteAll();
        catering = null;
        anotherCatering = null;
        address = null;
        dish = null;
        product = null;
    }

    @Test
    @DisplayName("Should correctly return all caterings")
    void getAllCaterings() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/caterings"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].agreedPrice", is(7500.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].agreedPrice", is(5000.0)));
    }

    @Test
    @DisplayName("Should return correct catering")
    void getCatering() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/caterings/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.agreedPrice", is(7500.0)));
    }

    @Test
    @DisplayName("Should create correct catering")
    void createCatering() throws Exception {
        String requestJson = """
                {
                     "dateAndTime" : "31-10-2024 17:00",
                     "numberOfPeople" : 25,
                     "agreedPrice" : 1500,
                     "notes" : "create succes"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.post("/caterings")
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.agreedPrice", is(1500.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.notes", is("create succes")));
    }

    @Test
    @DisplayName("Should delete correct catering")
    void deleteCatering() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/caterings/{id}", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }

    @Test
    @DisplayName("Should correctly update catering")
    void updateCatering() throws Exception {
        String requestJson = """
                {
                     "dateAndTime" : "31-10-2024 17:00",
                     "numberOfPeople" : 25,
                     "agreedPrice" : 1500,
                     "notes" : "update succes"
                }
                """;

        mockMvc.perform(MockMvcRequestBuilders.put("/caterings/{id}", 1)
                        .contentType(APPLICATION_JSON)
                        .content(requestJson))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.agreedPrice", is(1500.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.notes", is("update succes")));
    }

    @Test
    @DisplayName("Should correctly set catering address")
    void setCateringAddress() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/caterings/{id}/address?addressId={addressId}", 1, 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.name", is("Party House")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.street", is("Feeststraat")))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.houseNumber", is(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address.city", is("Feeststad")));
    }

    @Test
    @DisplayName("Should correctly add product to catering")
    void addProductToCatering() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/caterings/{id}/products?productId={productId}", 1, 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Added product 1 to catering.", response);
    }

    @Test
    @DisplayName("Should correctly remove product from catering")
    void removeProductFromCatering() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/caterings/{id}/products?productId={productId}", 1, 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Product with id 1 removed from catering.", response);
    }

    @Test
    @DisplayName("Should correctly add dish to catering")
    void addDishToCatering() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/caterings/{id}/dishes?dishId={dishId}", 1, 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Added dish 1 to catering.", response);
    }

    @Test
    @DisplayName("Should correctly remove dish from catering")
    void removeDishFromCatering() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.delete("/caterings/{id}/dishes?dishId={dishId}", 1, 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Dish with id 1 removed from catering.", response);
    }

    @Test
    @DisplayName("Should set correct agreed catering price")
    void setAgreedCateringPrice() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/caterings/{id}/prices/agreed-price?agreedPrice={agreedPrice}", 1, 12345))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Price of catering 1 set to 12345.0.", response);
    }

    @Test
    @DisplayName("Should set correct prices for catering")
    void calculateCateringPrices() throws Exception {
        MvcResult result = this.mockMvc
                .perform(MockMvcRequestBuilders.post("/caterings/{id}/prices?laborAndMaterialCost={laborAndMaterialCost}", 1, 500))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String response = result.getResponse().getContentAsString();

        assertEquals("Catering prices calculated. Cost price: 506.2 and sell price: 762.2", response);
    }

    @Test
    @DisplayName("Should set prices of catering to zero")
    void resetCateringPrices() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/dishes/{id}/prices/reset", 1))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.productionPrice", is(0.0)))
                .andExpect(MockMvcResultMatchers.jsonPath("$.sellPrice", is(0.0)));
    }
}