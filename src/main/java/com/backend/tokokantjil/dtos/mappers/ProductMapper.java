package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.ProductInputDto;
import com.backend.tokokantjil.dtos.outputs.ProductOutputDto;
import com.backend.tokokantjil.models.Product;

public class ProductMapper {

    public static Product fromProductInputDtoToProduct(ProductInputDto productInputDto) {
        Product product = new Product();

        product.setName(productInputDto.name);
        product.setState(productInputDto.state);
        product.setAmount(productInputDto.amount);
        product.setAmountUnit(productInputDto.amountUnit);
        product.setBuyPrice(productInputDto.buyPrice);
        product.setSellPrice(productInputDto.sellPrice);
        product.setForRetail(productInputDto.isForRetail);
        product.setNotes(productInputDto.notes);

        return product;
    }

    public static ProductOutputDto fromProductToProductOutputDto(Product product) {
        ProductOutputDto productOutputDto = new ProductOutputDto();

        productOutputDto.setId(product.getId());
        productOutputDto.setName(product.getName());
        productOutputDto.setState(product.getState());
        productOutputDto.setAmount(product.getAmount());
        productOutputDto.setAmountUnit(product.getAmountUnit());
        productOutputDto.setBuyPrice(product.getBuyPrice());
        productOutputDto.setSellPrice(product.getSellPrice());
        productOutputDto.setForRetail(product.isForRetail());
        productOutputDto.setNotes(product.getNotes());

        return productOutputDto;
    }

    public static Product fromProductToUpdatedProduct(Product product, Product productUpdate) {

        product.setName(productUpdate.getName());
        product.setState(productUpdate.getState());
        product.setAmount(productUpdate.getAmount());
        product.setAmountUnit(productUpdate.getAmountUnit());
        product.setBuyPrice(productUpdate.getBuyPrice());
        product.setSellPrice(productUpdate.getSellPrice());
        product.setForRetail(productUpdate.isForRetail());
        product.setNotes(productUpdate.getNotes());

        return product;
    }
}
