package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.OrderInputDto;
import com.backend.tokokantjil.dtos.outputs.OrderOutputDto;
import com.backend.tokokantjil.dtos.outputs.ProductOutputDto;
import com.backend.tokokantjil.models.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order fromOrderInputDtoToOrder(OrderInputDto orderInputDto) {
        Order order = new Order();

        order.setTitle(orderInputDto.title);
        order.setStatus(orderInputDto.status);
        order.setCatering(orderInputDto.isCatering);

        return order;
    }

    public static OrderOutputDto fromOrderToOrderOutputDto(Order order) {
        OrderOutputDto orderOutputDto = new OrderOutputDto();

        orderOutputDto.setId(order.getId());
        orderOutputDto.setTitle(order.getTitle());
        orderOutputDto.setCreatedAt(order.getCreatedAt());
        orderOutputDto.setStatus(order.getStatus());
        orderOutputDto.setCatering(order.isCatering());

        if (order.getProducts() != null) {
            List<ProductOutputDto> productOutputDtoList = new ArrayList<>();
            for (int i = order.getProducts().size(); i < order.getProducts().size(); i++) {
                productOutputDtoList.add(ProductMapper.fromProductToProductOutputDto(order.getProducts().get(i)));
            }
            orderOutputDto.setProductOutputDtos(productOutputDtoList);
        }

        return orderOutputDto;
    }

    public static Order fromOrderToUpdatedOrder(Order order, Order orderUpdate) {

        order.setTitle(orderUpdate.getTitle());
        order.setStatus(orderUpdate.getStatus());
        order.setCatering(orderUpdate.isCatering());

        return order;
    }
}
