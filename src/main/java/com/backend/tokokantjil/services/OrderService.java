package com.backend.tokokantjil.services;

import com.backend.tokokantjil.dtos.inputs.OrderInputDto;
import com.backend.tokokantjil.dtos.mappers.OrderMapper;
import com.backend.tokokantjil.dtos.outputs.CateringOutputDto;
import com.backend.tokokantjil.dtos.outputs.OrderOutputDto;
import com.backend.tokokantjil.enumerations.Status;
import com.backend.tokokantjil.exceptions.RecordNotFoundException;
import com.backend.tokokantjil.exceptions.UserInputIsUnprocessableException;
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

    public OrderOutputDto assignCateringToOrder(Long id, Long cateringId) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        Catering catering = this.cateringRepository.findById(cateringId).orElseThrow(() -> new RecordNotFoundException("No catering with id " + cateringId + " found."));

        if (order.isCateringOrder()) {
            if (catering.getAddress() != null) {
                order.setCatering(catering);
                order.setAppraised(false);
                this.orderRepository.save(order);

                return OrderMapper.fromOrderToOrderOutputDto(order);
            } else {
                throw new UserInputIsUnprocessableException("Unable to add catering " + cateringId + " to order. Catering has no address set!");
            }
        } else {
            throw new UserInputIsUnprocessableException("Order " + id + " is not set as a catering.");
        }
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

    public OrderOutputDto addProductToOrder(Long id, Long productId) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        Product product = this.productRepository.findById(productId).orElseThrow(() -> new RecordNotFoundException("No product with id " + productId + " found."));

        if (order.isCateringOrder()) {
            throw new UserInputIsUnprocessableException("Unable to add any product to order. Order is set as catering.");
        } else {
            if (product.isForRetail()) {
                order.setAppraised(false);
                order.getProducts().add(product);
                this.orderRepository.save(order);

                return OrderMapper.fromOrderToOrderOutputDto(order);
            } else {
                throw new UserInputIsUnprocessableException("Unable to add product " + productId + ". Product is not for retail.");
            }
        }
    }

    public String removeProductFromOrder(Long id, Long productId) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        String response = "";
        List<Product> productList = order.getProducts();

        for (Product product : order.getProducts()) {
            if (product.getId().equals(productId)) {
                productList.remove(product);
                order.setAppraised(false);
                response = "Product with id " + productId + " removed from order.";
                break;
            }
        }

        if (response.isEmpty()) {
            throw new RecordNotFoundException("No product with id " + productId + " found in order " + id + ".");
        } else {
            order.setProducts(productList);
            this.orderRepository.save(order);

            return response;
        }
    }

    public OrderOutputDto addDishToListOfOrder(Long id, Long dishId) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        Dish dish = this.dishRepository.findById(dishId).orElseThrow(() -> new RecordNotFoundException("No dish with id " + dishId + " found."));

        if (order.isCateringOrder()) {
            throw new UserInputIsUnprocessableException("Unable to add any dish to order. Order is set as catering.");
        } else {
            if (dish.isAppraised()) {
                order.setAppraised(false);
                order.getDishes().add(dish);
                this.orderRepository.save(order);
                return OrderMapper.fromOrderToOrderOutputDto(order);
            } else {
                throw new UserInputIsUnprocessableException("Unable to add dish to order. Set prices dish " + dishId + " first.");
            }
        }
    }

    public String removeDishFromListOfOrder(Long id, Long dishId) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        String response = "";
        List<Dish> dishList = order.getDishes();

        for (Dish dish : order.getDishes()) {
            if (dish.getId().equals(dishId)) {
                dishList.remove(dish);
                order.setAppraised(false);
                response = "Dish with id " + dishId + " removed from order.";
                break;
            }
        }

        if (response.isEmpty()) {
            throw new RecordNotFoundException("No dish with id " + dishId + " found in order " + id + ".");
        } else {
            order.setDishes(dishList);
            this.orderRepository.save(order);

            return response;
        }
    }

    public String calculateOrderTotalPrices(Long id) {
        Order order = this.orderRepository.findById(id).orElseThrow(() -> new RecordNotFoundException("No order with id " + id + " found."));
        String response = "";

        if (order.isAppraised()) {
            throw new UserInputIsUnprocessableException("Order prices are already calculated. Reset prices first if you want to recalculate them.");
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
                        throw new UserInputIsUnprocessableException("Prices of catering must be calculated first.");
                    }
                } else {
                    throw new UserInputIsUnprocessableException("Assign a catering to order first or set order as not a catering.");
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
                    throw new UserInputIsUnprocessableException("Prices of every dish must be calculated first.");
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
