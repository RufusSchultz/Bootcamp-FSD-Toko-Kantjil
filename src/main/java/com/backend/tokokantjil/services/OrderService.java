package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.OrderInputDto;
import com.backend.tokokantjil.dtos.mappers.OrderMapper;
import com.backend.tokokantjil.dtos.outputs.OrderOutputDto;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.models.Order;
import com.backend.tokokantjil.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<OrderOutputDto> getAllOrders() {
        List<OrderOutputDto> list = new ArrayList<>();
        for (Order i: this.orderRepository.findAll()) {
            list.add(OrderMapper.fromOrderToOrderOutputDto(i));
        }
        return list;
    }

    public OrderOutputDto getOrderById(Long id) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        return OrderMapper.fromOrderToOrderOutputDto(order);
    }

    public OrderOutputDto createOrder(OrderInputDto orderInputDto) {
        Order order = this.orderRepository.save(OrderMapper.fromOrderInputDtoToOrder(orderInputDto));
        return OrderMapper.fromOrderToOrderOutputDto(order);
    }

    public void deleteOrder(Long id) {
        if(this.orderRepository.findById(id).isPresent()) {
            this.orderRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No order with id " + id + " found.");
        }
    }

    public OrderOutputDto updateOrder(Long id, OrderInputDto orderInputDto) {
        Order oldOrder = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        Order orderUpdate = OrderMapper.fromOrderInputDtoToOrder(orderInputDto);
        Order newOrder = this.orderRepository.save(OrderMapper.fromOrderToUpdatedOrder(oldOrder, orderUpdate));

        return OrderMapper.fromOrderToOrderOutputDto(newOrder);
    }
}
