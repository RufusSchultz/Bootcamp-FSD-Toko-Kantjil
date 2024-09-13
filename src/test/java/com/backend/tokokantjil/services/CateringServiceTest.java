package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.CateringInputDto;
import com.backend.tokokantjil.dtos.outputs.CateringOutputDto;
import com.backend.tokokantjil.dtos.outputs.DishOutputDto;
import com.backend.tokokantjil.enumerations.State;
import com.backend.tokokantjil.models.Address;
import com.backend.tokokantjil.models.Catering;
import com.backend.tokokantjil.models.Dish;
import com.backend.tokokantjil.models.Product;
import com.backend.tokokantjil.repositories.AddressRepository;
import com.backend.tokokantjil.repositories.CateringRepository;
import com.backend.tokokantjil.repositories.DishRepository;
import com.backend.tokokantjil.repositories.ProductRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class CateringServiceTest {
    Catering catering;
    Catering anotherCatering;
    CateringInputDto cateringInputDto;
    Product product;
    Dish dish;
    Address address;

    @Mock
    CateringRepository cateringRepository;
    @Mock
    ProductRepository productRepository;
    @Mock
    DishRepository dishRepository;
    @Mock
    AddressRepository addressRepository;

    @InjectMocks
    CateringService service;

    @BeforeEach
    void setUp() {
        product = new Product("sambal", State.packaged, 300, "gram", 1.20, 2.20, true, 10, "");
        List<Product> productList = new ArrayList<>();
        productList.add(product);
        dish = new Dish("main", 6, 5, 10, true, 1, "", null, null, null);
        List<Dish> dishList = new ArrayList<>();
        dishList.add(dish);
        address = new Address("Party House", "Feeststraat", 1, null, "1234AB", "Feeststad", "", null, null);
        catering = new Catering(LocalDateTime.of(2024, 12, 5, 15, 30), 10, 0, 0, 7500, false, "", productList, dishList, address, null);
        anotherCatering = new Catering(LocalDateTime.of(2024, 11, 5, 15, 30), 100, 0, 0, 5000, true, "", null, null, null, null);
        cateringInputDto = new CateringInputDto(
                "05-12-2024 15:30",
                catering.getNumberOfPeople(),
                catering.getAgreedPrice(),
                catering.getNotes()
        );
    }

    @AfterEach
    void tearDown() {
        catering = null;
        anotherCatering = null;
        cateringInputDto = null;
        product = null;
        dish = null;
        address = null;
    }

    @Test
    @DisplayName("Should return every catering")
    void getEveryCatering() {
        List<Catering> mockList = new ArrayList<>();
        mockList.add(catering);
        mockList.add(anotherCatering);
        Mockito.when(cateringRepository.findAll()).thenReturn(mockList);

        List<CateringOutputDto> testList = service.getEveryCatering();

        assertEquals(2, testList.size());
    }

    @Test
    @DisplayName("Should return correct catering")
    void getCateringById() {
        catering.setId(1L);
        Mockito.when(cateringRepository.findById(anyLong())).thenReturn(Optional.of(catering));

        CateringOutputDto cateringOutputDto = service.getCateringById(1L);

        assertEquals(LocalDateTime.of(2024, 12, 5, 15, 30), cateringOutputDto.getDateAndTime());
        assertEquals(10, cateringOutputDto.getNumberOfPeople());
        assertEquals(7500, cateringOutputDto.getAgreedPrice());
    }

    @Test
    @DisplayName("Should create correct catering")
    void createNewCatering() {
        Mockito.when(cateringRepository.save(any(Catering.class))).thenReturn(catering);

        CateringOutputDto cateringOutputDto = service.createNewCatering(cateringInputDto);

        assertEquals(LocalDateTime.of(2024, 12, 5, 15, 30), cateringOutputDto.getDateAndTime());
        assertEquals(10, cateringOutputDto.getNumberOfPeople());
        assertEquals(7500, cateringOutputDto.getAgreedPrice());
    }

    @Test
    @DisplayName("Should delete correct catering")
    void deleteCateringById() {
        catering.setId(1L);
        Mockito.when(cateringRepository.findById(1L)).thenReturn(Optional.of(catering));

        service.deleteCateringById(1L);

        Mockito.verify(cateringRepository).deleteById(any());
    }

    @Test
    @DisplayName("Should correctly update catering")
    void updateCateringWithNewCateringInputDto() {
        anotherCatering.setId(1L);
        Mockito.when(cateringRepository.findById(anyLong())).thenReturn(Optional.of(anotherCatering));
        Mockito.when(cateringRepository.save(any(Catering.class))).thenReturn(anotherCatering);

        service.updateCateringWithNewCateringInputDto(1L, cateringInputDto);

        assertEquals(LocalDateTime.parse("05-12-2024 15:30", DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")), anotherCatering.getDateAndTime());
        assertEquals(10, anotherCatering.getNumberOfPeople());
    }

    @Test
    @DisplayName("Should set address of catering correctly")
    void setCateringAddress() {
        catering.setId(1L);
        address.setId(1L);
        Mockito.when(cateringRepository.findById(anyLong())).thenReturn(Optional.of(catering));
        Mockito.when(addressRepository.findById(anyLong())).thenReturn(Optional.of(address));
        Mockito.when(cateringRepository.save(any(Catering.class))).thenReturn(catering);

        service.setCateringAddress(1L, 1L);

        assertEquals("Party House", catering.getAddress().getName());
        assertEquals("Feeststraat", catering.getAddress().getStreet());
        assertEquals(1, catering.getAddress().getHouseNumber());
        assertEquals("Feeststad", catering.getAddress().getCity());
    }

    @Test
    @DisplayName("Should add product to catering correctly")
    void addProductToListOfCatering() {
        catering.setId(1L);
        product.setId(1L);
        Mockito.when(cateringRepository.findById(anyLong())).thenReturn(Optional.of(catering));
        Mockito.when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        Mockito.when(cateringRepository.save(any(Catering.class))).thenReturn(catering);

        service.addProductToListOfCatering(1L, 1L);

        assertEquals("sambal", catering.getProducts().get(1).getName());
        assertEquals(2, catering.getProducts().size());
    }

    @Test
    @DisplayName("Should remove product from catering correctly")
    void removeProductFromListOfCatering() {
        catering.setId(1L);
        product.setId(1L);
        Mockito.when(cateringRepository.findById(anyLong())).thenReturn(Optional.of(catering));
        Mockito.when(cateringRepository.save(any(Catering.class))).thenReturn(catering);

        String response = service.removeProductFromListOfCatering(1L, 1L);

        assertEquals("Product with id 1 removed from catering.", response);
        assertEquals(0, catering.getProducts().size());
    }

    @Test
    @DisplayName("Should add dish to catering correctly")
    void addDishToListOfCatering() {
        catering.setId(1L);
        dish.setId(1L);
        Mockito.when(cateringRepository.findById(anyLong())).thenReturn(Optional.of(catering));
        Mockito.when(dishRepository.findById(anyLong())).thenReturn(Optional.of(dish));
        Mockito.when(cateringRepository.save(any(Catering.class))).thenReturn(catering);

        service.addDishToListOfCatering(1L, 1L);

        assertEquals("main", catering.getDishes().get(1).getName());
        assertEquals(2, catering.getDishes().size());
    }

    @Test
    @DisplayName("Should remove dish from catering correctly")
    void removeDishFromListOfCatering() {
        catering.setId(1L);
        dish.setId(1L);
        Mockito.when(cateringRepository.findById(anyLong())).thenReturn(Optional.of(catering));
        Mockito.when(cateringRepository.save(any(Catering.class))).thenReturn(catering);

        String response = service.removeDishFromListOfCatering(1L, 1L);

        assertEquals("Dish with id 1 removed from catering.", response);
        assertEquals(0, catering.getDishes().size());
    }

    @Test
    @DisplayName("Should set correct agreed price of catering")
    void setAgreedPriceOfCatering() {
        catering.setId(1L);
        Mockito.when(cateringRepository.findById(anyLong())).thenReturn(Optional.of(catering));
        Mockito.when(cateringRepository.save(any(Catering.class))).thenReturn(catering);

        String response = service.setAgreedPriceOfCatering(1L, 1234);

        assertEquals("Agreed price of catering 1 set to 1234.0.", response);
        assertEquals(1234, catering.getAgreedPrice());
    }

    @Test
    @DisplayName("Should calculate catering prices correctly")
    void calculateCateringPrices() {
        catering.setId(1L);
        Mockito.when(cateringRepository.findById(anyLong())).thenReturn(Optional.of(catering));
        Mockito.when(cateringRepository.save(any(Catering.class))).thenReturn(catering);

        String response = service.calculateCateringPrices(1L, 750);

        assertEquals("Catering prices calculated. Cost price: 756.2 and sell price: 1137.2", response);
        assertEquals(756.2, catering.getTotalCostPrice());
        assertEquals(1137.2, catering.getTotalSellPrice());
        assertTrue(catering.isAppraised());
    }

    @Test
    @DisplayName("Should set prices of catering to zero correctly")
    void setCateringPricesToZero() {
        anotherCatering.setId(1L);
        Mockito.when(cateringRepository.findById(anyLong())).thenReturn(Optional.of(anotherCatering));
        Mockito.when(cateringRepository.save(any(Catering.class))).thenReturn(anotherCatering);

        service.setCateringPricesToZero(1L);

        assertEquals(0, anotherCatering.getTotalCostPrice());
        assertEquals(0, anotherCatering.getTotalSellPrice());
        assertFalse(anotherCatering.isAppraised());
    }
}