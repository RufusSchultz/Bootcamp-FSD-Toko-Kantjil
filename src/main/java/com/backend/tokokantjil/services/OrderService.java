package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.OrderInputDto;
import com.backend.tokokantjil.dtos.mappers.OrderMapper;
import com.backend.tokokantjil.dtos.outputs.OrderOutputDto;
import com.backend.tokokantjil.enumerations.Status;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.models.*;
import com.backend.tokokantjil.repositories.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static com.backend.tokokantjil.helpers.EnumInputChecker.enumInputChecker;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final CateringRepository cateringRepository;
    private final ProductRepository productRepository;
    private final DishRepository dishRepository;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, CateringRepository cateringRepository, ProductRepository productRepository, DishRepository dishRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.cateringRepository = cateringRepository;
        this.productRepository = productRepository;
        this.dishRepository = dishRepository;
        this.userRepository = userRepository;
    }

    public List<OrderOutputDto> getAllOrders() {
        List<OrderOutputDto> list = new ArrayList<>();
        for (Order i : this.orderRepository.findAll()) {
            list.add(OrderMapper.fromOrderToOrderOutputDto(i));
        }
        return list;
    }

    public OrderOutputDto getOrderById(Long id) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        return OrderMapper.fromOrderToOrderOutputDto(order);
    }

    public OrderOutputDto createOrder(UserDetails userDetails, OrderInputDto orderInputDto) {
        Order order = OrderMapper.fromOrderInputDtoToOrder(orderInputDto);

        order.setUser(this.userRepository.findByUsername(userDetails.getUsername()));
        this.orderRepository.save(order);
        if (order.getTitle() == null || order.getTitle().isEmpty()) {
            order.setTitle("Order " + order.getId());
            this.orderRepository.save(order);
        }

        return OrderMapper.fromOrderToOrderOutputDto(order);
    }

    public void deleteOrder(Long id) {
        if (this.orderRepository.findById(id).isPresent()) {
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

    public OrderOutputDto assignCustomerToOrder(Long id, Long customerId) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        Customer customer = this.customerRepository.findById(customerId).orElseThrow(() -> new RecordNotFoundException("No customer with id " + customerId + " found."));

        order.setCustomer(customer);
        this.orderRepository.save(order);

        return OrderMapper.fromOrderToOrderOutputDto(order);
    }

    public String assignCateringToOrder(Long id, Long cateringId) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        Catering catering = this.cateringRepository.findById(cateringId).orElseThrow(() -> new RecordNotFoundException("No catering with id " + cateringId + " found."));
        String response = "";

        if (order.isCateringOrder()) {
            if (order.getCatering().getAddress() != null){
                order.setCatering(catering);
                order.setAppraised(false);
                this.orderRepository.save(order);

                response = "Catering " + order.getCatering().getId() + " assigned to order.";
            } else {
                response = "Unable to add catering " + cateringId + " to order. Catering has no address set!";
            }
         } else {
            response = "Order " + order.getId() + " is not set as a catering. Order is unchanged.";
        }
        return response;
    }

    public String setOrderStatus(Long id, String status) {
        status = status.toLowerCase();
        String[] statusList = Stream.of(Status.values()).map(Status::name).toArray(String[]::new);
        enumInputChecker(statusList, status);

        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        String response = "Status of order " + order.getId() + " set to ";


        if (status.equals("accepted")) {
            order.setStatus(Status.accepted);
            response = response + "accepted.";
        } else if (status.equals("processing")) {
            order.setStatus(Status.processing);
            response = response + "processing.";
        } else if (status.equals("done")) {
            order.setStatus(Status.done);
            response = response + "done.";
        }
        this.orderRepository.save(order);

        return response;
    }

    public String addProductToOrder(Long id, Long productId) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new RecordNotFoundException("No product with id " + productId + " found."));
        String response = "";

        if (order.isCateringOrder()) {
            response = "Unable to add any product to order. Order is set as catering. Order is unchanged.";
        } else {
            if (product.isForRetail()) {
                order.setAppraised(false);
                order.getProducts().add(product);
                this.orderRepository.save(order);

                response = "Added product " + productId + " to order.";
            } else {
                response = "Unable to add product " + productId + ". Product is not for retail.";
            }
        }

        return response;
    }

    public String removeProductFromOrder(Long id, Long productId) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        String response = "No product with id " + productId + " found. Order is unchanged.";
        List<Product> productList = order.getProducts();

        for (Product product : order.getProducts()) {
            if (product.getId().equals(productId)) {
                productList.remove(product);
                order.setAppraised(false);
                response = "Product with id " + productId + " removed from order.";
                break;
            }
        }
        order.setProducts(productList);
        this.orderRepository.save(order);

        return response;
    }

    public String addDishToListOfOrder(Long id, Long dishId) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        Dish dish = this.dishRepository.findById(dishId).orElseThrow(() -> new RecordNotFoundException("No dish with id " + dishId + " found."));
        String response = "";

        if (order.isCateringOrder()) {
            response = "Unable to add any dish to order. Order is set as catering. Order is unchanged.";
        } else {
            if (dish.isAppraised()) {
                order.setAppraised(false);
                order.getDishes().add(dish);
                this.orderRepository.save(order);
                response = "Added dish " + dish.getId() + " to order.";
            } else {
                response = "Unable to add dish to order. Set prices dish " + dish.getId() + " first.";
            }
        }

        return response;
    }

    public String removeDishFromListOfOrder(Long id, Long dishId) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        String response = "No dish with id " + dishId + " found. Order is unchanged.";
        List<Dish> dishList = order.getDishes();

        for (Dish dish : order.getDishes()) {
            if (dish.getId().equals(dishId)) {
                dishList.remove(dish);
                order.setAppraised(false);
                response = "Dish with id " + dishId + " removed from order.";
                break;
            }
        }
        order.setDishes(dishList);
        this.orderRepository.save(order);

        return response;
    }

    public String calculateOrderTotalPrices(Long id) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        String response = "";

        if (order.isAppraised()) {
            response = "Order prices are already calculated. Reset prices first if you want to recalculate them.";

        } else {
            if (order.isCateringOrder()) {
                if (order.getCatering() != null) {
                    if (order.getCatering().isAppraised()) {
                        order.setTotalPrice(order.getCatering().getTotalSellPrice());
                        order.setTotalCost(order.getCatering().getTotalCostPrice());
                        order.setAppraised(true);
                        this.orderRepository.save(order);

                        response = "Order prices set to catering prices. Total sell price: " + order.getTotalPrice() + " and total cost price: " + order.getTotalCost();
                    } else {
                        response = "Prices of catering must be calculated first. Order is unchanged.";
                    }
                } else {
                    response = "Assign a catering to order first or set order as not a catering. Order is unchanged.";
                }
            } else {
                boolean killSwitch = false;
                double totalSellPrice = 0;
                double totalCostPrice = 0;

                for (Product product : order.getProducts()) {
                    totalSellPrice += product.getSellPrice();
                    totalCostPrice += product.getBuyPrice();
                }
                for (Dish dish : order.getDishes()) {
                    if (dish.isAppraised()) {
                        totalSellPrice += dish.getSellPrice();
                        totalCostPrice += dish.getProductionPrice();
                    } else {
                        killSwitch = true;
                        break;
                    }
                }
                if (!killSwitch) {
                    order.setTotalPrice(totalSellPrice);
                    order.setTotalCost(totalCostPrice);
                    order.setAppraised(true);
                    this.orderRepository.save(order);

                    response = "Order prices calculated. Total sell price: " + order.getTotalPrice() + " and total cost price: " + order.getTotalCost();
                } else {
                    response = "Prices of every dish must be calculated first. Order is unchanged.";
                }
            }
        }
        return response;
    }

    public OrderOutputDto setOrderPricesToZero(Long id) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));

        order.setAppraised(false);
        order.setTotalCost(0);
        order.setTotalPrice(0);
        this.orderRepository.save(order);

        return OrderMapper.fromOrderToOrderOutputDto(order);
    }
}
