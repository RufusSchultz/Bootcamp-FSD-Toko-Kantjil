package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.ProductInputDto;
import com.backend.tokokantjil.dtos.outputs.ProductOutputDto;
import com.backend.tokokantjil.enumerations.State;
import com.backend.tokokantjil.models.Product;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    Product product;
    Product anotherProduct;
    ProductInputDto productInputDto;

    @Mock
    ProductRepository productRepository;

    @InjectMocks
    ProductService service;

    @BeforeEach
    void setUp() {
        product = new Product("sambal", State.packaged, 300, "gram", 1.20, 2.20, true, 10, "");
        anotherProduct = new Product("rice", State.packaged, 10, "kilo", 50, 100, false, 1, "");
        productInputDto = new ProductInputDto(
                product.getName(),
                product.getState().toString(),
                product.getAmount(),
                product.getAmountUnit(),
                product.getBuyPrice(),
                product.getSellPrice(),
                product.isForRetail(),
                product.getStock(),
                product.getNotes()
        );
    }

    @AfterEach
    void tearDown() {
        product = null;
        anotherProduct = null;
        productInputDto = null;
    }

    @Test
    @DisplayName("Should return every product")
    void getEveryProduct() {
        List<Product> mockList = new ArrayList<>();
        mockList.add(product);
        mockList.add(anotherProduct);
        Mockito.when(productRepository.findAll()).thenReturn(mockList);

        List<ProductOutputDto> testList = service.getEveryProduct();

        assertEquals(2, testList.size());
    }

    @Test
    @DisplayName("Should return correct product")
    void getProductById() {
        product.setId(1L);
        Mockito.when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));

        ProductOutputDto productOutputDto = service.getProductById(1L);

        assertEquals("sambal", productOutputDto.getName());
        assertEquals("packaged", productOutputDto.getState());
        assertEquals(300, productOutputDto.getAmount());
        assertEquals("gram", productOutputDto.getAmountUnit());
        assertEquals(1.20, productOutputDto.getBuyPrice());
        assertEquals(2.20, productOutputDto.getSellPrice());
        assertTrue(productOutputDto.isForRetail());
        assertEquals(10, productOutputDto.getStock());
        assertEquals("", productOutputDto.getNotes());
    }

    @Test
    @DisplayName("Should create correct product")
    void createNewProduct() {
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);

        ProductOutputDto productOutputDto = service.createNewProduct(productInputDto);

        assertEquals("sambal", productOutputDto.getName());
        assertEquals("packaged", productOutputDto.getState());
        assertEquals(300, productOutputDto.getAmount());
        assertEquals("gram", productOutputDto.getAmountUnit());
        assertEquals(1.20, productOutputDto.getBuyPrice());
        assertEquals(2.20, productOutputDto.getSellPrice());
        assertTrue(productOutputDto.isForRetail());
        assertEquals(10, productOutputDto.getStock());
        assertEquals("", productOutputDto.getNotes());
    }

    @Test
    @DisplayName("Should delete correct product")
    void deleteProductById() {
        product.setId(1L);
        Mockito.when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        service.deleteProductById(1L);

        Mockito.verify(productRepository).deleteById(any());
    }

    @Test
    @DisplayName("Should update correct product")
    void updateProductWithNewProductInputDto() {
        anotherProduct.setId(1L);
        Mockito.when(productRepository.findById(anyLong())).thenReturn(Optional.of(anotherProduct));
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(anotherProduct);

        ProductOutputDto productOutputDto = service.updateProductWithNewProductInputDto(1L, productInputDto);

        assertEquals("sambal", productOutputDto.getName());
        assertEquals("packaged", productOutputDto.getState());
        assertEquals(300, productOutputDto.getAmount());
        assertEquals("gram", productOutputDto.getAmountUnit());
        assertEquals(1.20, productOutputDto.getBuyPrice());
        assertEquals(2.20, productOutputDto.getSellPrice());
        assertTrue(productOutputDto.isForRetail());
        assertEquals(10, productOutputDto.getStock());
        assertEquals("", productOutputDto.getNotes());
    }

    @Test
    @DisplayName("Should correctly alter stock of product")
    void alterProductStock() {
        product.setId(1L);
        Mockito.when(productRepository.findById(anyLong())).thenReturn(Optional.of(product));
        Mockito.when(productRepository.save(any(Product.class))).thenReturn(product);

        service.alterProductStock(1L, 1);

        assertEquals(11, product.getStock());
    }
}