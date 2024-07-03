package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.ProductInputDto;
import com.backend.tokokantjil.dtos.outputs.ProductOutputDto;
import com.backend.tokokantjil.models.Product;

public class ProductMapper {

    public static Product fromProductInputDtoToProduct(ProductInputDto productInputDto) {
        Product p = new Product();

        p.setName(productInputDto.name);
        p.setState(productInputDto.state);
        p.setAmount(productInputDto.amount);
        p.setAmountUnit(productInputDto.amountUnit);
        p.setBuyPrice(productInputDto.buyPrice);
        p.setSellPrice(productInputDto.sellPrice);
        p.setForRetail(productInputDto.isForRetail);
        p.setNotes(productInputDto.notes);

        return p;
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

    public static Product fromProductToUpdatedProduct(Product p, Product pUpdate) {

        p.setName(pUpdate.getName());
        p.setState(pUpdate.getState());
        p.setAmount(pUpdate.getAmount());
        p.setAmountUnit(pUpdate.getAmountUnit());
        p.setBuyPrice(pUpdate.getBuyPrice());
        p.setSellPrice(pUpdate.getSellPrice());
        p.setForRetail(pUpdate.isForRetail());
        p.setNotes(pUpdate.getNotes());

        return p;
    }
}
