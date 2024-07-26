package com.backend.tokokantjil.dtos.mappers;

import com.backend.tokokantjil.dtos.inputs.OrderInputDto;
import com.backend.tokokantjil.dtos.outputs.DishOutputDto;
import com.backend.tokokantjil.dtos.outputs.OrderOutputDto;
import com.backend.tokokantjil.dtos.outputs.ProductOutputDto;
import com.backend.tokokantjil.enumerations.Status;
import com.backend.tokokantjil.models.Dish;
import com.backend.tokokantjil.models.Order;
import com.backend.tokokantjil.models.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OrderMapper {

    public static Order fromOrderInputDtoToOrder(OrderInputDto orderInputDto) {
//        LocalDateTime thisVeryMoment = new

        Order order = new Order();

        order.setTitle(orderInputDto.title);
//        order.setCreatedAt();
        order.setStatus(Status.accepted);
        order.setCateringOrder(orderInputDto.isCateringOrder);
        order.setAppraised(false);

        return order;
    }

    public static OrderOutputDto fromOrderToOrderOutputDto(Order order) {
        OrderOutputDto orderOutputDto = new OrderOutputDto();

        orderOutputDto.setId(order.getId());
        orderOutputDto.setTitle(order.getTitle());
        orderOutputDto.setCreatedAt(order.getCreatedAt());
        orderOutputDto.setStatus(order.getStatus());
        orderOutputDto.setTotalPrice(order.getTotalPrice());
        orderOutputDto.setTotalCost(order.getTotalCost());
        orderOutputDto.setCateringOrder(order.isCateringOrder());
        orderOutputDto.setAppraised(order.isAppraised());

        if (order.getProducts() != null) {
            List<ProductOutputDto> productOutputDtoList = new ArrayList<>();
            for (Product product : order.getProducts()) {
                productOutputDtoList.add(ProductMapper.fromProductToProductOutputDto(product));
            }
            orderOutputDto.setProducts(productOutputDtoList);
        }
        if (order.getDishes() != null) {
            List<DishOutputDto> dishOutputDtoList = new ArrayList<>();
            for (Dish dish : order.getDishes()) {
                dishOutputDtoList.add(DishMapper.fromDishToDishOutputDto(dish));
            }
            orderOutputDto.setDishes(dishOutputDtoList);
        }
        if (order.getCatering() != null) {
            orderOutputDto.setCatering(CateringMapper.fromCateringToCateringOutputDto(order.getCatering()));
        }
        if (order.getCustomer() != null) {
            orderOutputDto.setCustomer(CustomerMapper.fromCustomerToCustomerOutputDto(order.getCustomer()));
        }
        if (order.getUser() != null) {
            orderOutputDto.setCreatedBy(order.getUser().getUsername());
        }
        return orderOutputDto;
    }

    public static Order fromOrderToUpdatedOrder(Order order, Order orderUpdate) {

        order.setTitle(orderUpdate.getTitle());
        order.setCateringOrder(orderUpdate.isCateringOrder());

        return order;
    }
}
