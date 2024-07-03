package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.ProductInputDto;
import com.backend.tokokantjil.dtos.outputs.ProductOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.dtos.mappers.ProductMapper;
import com.backend.tokokantjil.models.Product;
import com.backend.tokokantjil.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductOutputDto> getAllProducts() {
        List<ProductOutputDto> list = new ArrayList<>();
        for (Product i: this.productRepository.findAll()) {
            list.add(ProductMapper.fromProductToProductOutputDto(i));
        }
        return list;
    }

    public ProductOutputDto getProductById(Long id) {
        Product p = this.productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No product with id " + id + " found."));
        return ProductMapper.fromProductToProductOutputDto(p);
    }

    public ProductOutputDto createProduct(ProductInputDto productInputDto) {
        Product p = this.productRepository.save(ProductMapper.fromProductInputDtoToProduct(productInputDto));
        return ProductMapper.fromProductToProductOutputDto(p);
    }

    public void deleteProduct(Long id) {
        if(this.productRepository.findById(id).isPresent()) {
            this.productRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No product with id " + id + " found.");
        }
    }

    public ProductOutputDto updateProduct(Long id, ProductInputDto productInputDto) {
        Product p1 = this.productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No product with id " + id + " found."));
        Product p2 = ProductMapper.fromProductInputDtoToProduct(productInputDto);
        Product p3 = this.productRepository.save(ProductMapper.fromProductToUpdatedProduct(p1, p2));

        return ProductMapper.fromProductToProductOutputDto(p3);
    }
}
