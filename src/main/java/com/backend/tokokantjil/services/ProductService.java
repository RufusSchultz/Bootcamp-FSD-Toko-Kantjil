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

    public List<ProductOutputDto> getEveryProduct() {
        List<ProductOutputDto> list = new ArrayList<>();
        for (Product i : this.productRepository.findAll()) {
            list.add(ProductMapper.fromProductToProductOutputDto(i));
        }
        return list;
    }

    public ProductOutputDto getProductById(Long id) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No product with id " + id + " found."));
        return ProductMapper.fromProductToProductOutputDto(product);
    }

    public ProductOutputDto createNewProduct(ProductInputDto productInputDto) {
        Product product = this.productRepository.save(ProductMapper.fromProductInputDtoToProduct(productInputDto));
        return ProductMapper.fromProductToProductOutputDto(product);
    }

    public void deleteProductById(Long id) {
        if (this.productRepository.findById(id).isPresent()) {
            this.productRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No product with id " + id + " found.");
        }
    }

    public ProductOutputDto updateProductWithNewProductInputDto(Long id, ProductInputDto productInputDto) {
        Product oldProduct = this.productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No product with id " + id + " found."));
        Product productUpdate = ProductMapper.fromProductInputDtoToProduct(productInputDto);
        Product newProduct = this.productRepository.save(ProductMapper.fromProductToUpdatedProduct(oldProduct, productUpdate));

        return ProductMapper.fromProductToProductOutputDto(newProduct);
    }

    public ProductOutputDto increaseProductStock(Long id, int amount) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No product with id " + id + " found."));

        product.setStock(product.getStock() + amount);
        this.productRepository.save(product);

        return ProductMapper.fromProductToProductOutputDto(product);
    }

    public ProductOutputDto decreaseProductStock(Long id, int amount) {
        Product product = this.productRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No product with id " + id + " found."));

        product.setStock(product.getStock() - amount);
        this.productRepository.save(product);

        return ProductMapper.fromProductToProductOutputDto(product);
    }
}
