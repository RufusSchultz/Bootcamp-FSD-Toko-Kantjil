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
        Order p = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        return OrderMapper.fromOrderToOrderOutputDto(p);
    }

    public OrderOutputDto createOrder(OrderInputDto orderInputDto) {
        Order p = this.orderRepository.save(OrderMapper.fromOrderInputDtoToOrder(orderInputDto));
        return OrderMapper.fromOrderToOrderOutputDto(p);
    }

    public void deleteOrder(Long id) {
        if(this.orderRepository.findById(id).isPresent()) {
            this.orderRepository.deleteById(id);
        } else {
            throw new RecordNotFoundException("No order with id " + id + " found.");
        }
    }

    public OrderOutputDto updateOrder(Long id, OrderInputDto orderInputDto) {
        Order o1 = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        Order o2 = OrderMapper.fromOrderInputDtoToOrder(orderInputDto);
        Order o3 = this.orderRepository.save(OrderMapper.fromOrderToUpdatedOrder(o1, o2));

        return OrderMapper.fromOrderToOrderOutputDto(o3);
    }
}
