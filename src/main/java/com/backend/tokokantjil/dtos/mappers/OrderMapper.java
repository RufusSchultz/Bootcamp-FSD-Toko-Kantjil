package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.OrderInputDto;
import com.backend.tokokantjil.dtos.outputs.OrderOutputDto;
import com.backend.tokokantjil.models.Order;

public class OrderMapper {

    public static Order fromOrderInputDtoToOrder(OrderInputDto orderInputDto) {
        Order o = new Order();

        o.setTitle(orderInputDto.title);
        o.setStatus(orderInputDto.status);
        o.setCatering(orderInputDto.isCatering);

        return o;
    }

    public static OrderOutputDto fromOrderToOrderOutputDto(Order order) {
        OrderOutputDto orderOutputDto = new OrderOutputDto();

        orderOutputDto.setId(order.getId());
        orderOutputDto.setTitle(order.getTitle());
        orderOutputDto.setCreatedAt(order.getCreatedAt());
        orderOutputDto.setStatus(order.getStatus());
        orderOutputDto.setCatering(order.isCatering());

        return orderOutputDto;
    }

    public static Order fromOrderToUpdatedOrder(Order o, Order oUpdate) {

        o.setTitle(oUpdate.getTitle());
        o.setStatus(oUpdate.getStatus());
        o.setCatering(oUpdate.isCatering());

        return o;
    }
}
