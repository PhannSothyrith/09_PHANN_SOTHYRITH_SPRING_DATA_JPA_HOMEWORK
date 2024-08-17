package org.example.spring_data_jpa_homework.service.serviceImpl;

import org.example.spring_data_jpa_homework.model.entity.Customer;
import org.example.spring_data_jpa_homework.model.entity.Order;
import org.example.spring_data_jpa_homework.model.entity.Product;
import org.example.spring_data_jpa_homework.model.entity.ProductOrder;
import org.example.spring_data_jpa_homework.model.enums.Status;
import org.example.spring_data_jpa_homework.model.request.OrderRequest;
import org.example.spring_data_jpa_homework.model.response.OrderResponse;
import org.example.spring_data_jpa_homework.model.response.ProductResponse;
import org.example.spring_data_jpa_homework.repository.CustomerRepository;
import org.example.spring_data_jpa_homework.repository.OrderRepository;
import org.example.spring_data_jpa_homework.repository.ProductRepository;
import org.example.spring_data_jpa_homework.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    public OrderServiceImpl(OrderRepository orderRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }


    @Override
    public OrderResponse createOrder(Long customerId ,List<OrderRequest> orderRequest) {
        Customer customer =customerRepository.findById(customerId).orElseThrow();

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        order.setStatus(Status.PENDING);

        List<ProductOrder> productOrders = orderRequest.stream().map(or -> {
            Product product = productRepository.findById(or.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            ProductOrder productOrder = new ProductOrder();
            productOrder.setProduct(product);
            productOrder.setQuantity(or.getQuantity());
            productOrder.setOrder(order);

            return productOrder;
        }).collect(Collectors.toList());

        order.setProductOrder(productOrders);

        Float totalAmount = (float) productOrders.stream()
                .mapToDouble(productOrder -> productOrder.getProduct().getUnitPrice() * productOrder.getQuantity())
                .sum();
        order.setTotalAmount(totalAmount);

        Order savedOrder = orderRepository.save(order);

        List<ProductResponse> productDTOList = savedOrder.getProductOrder().stream()
                .map(productOrder -> {
                    Product product = productOrder.getProduct();
                    return new ProductResponse(product.getId(), product.getProductName(), product.getUnitPrice(), product.getDescription());
                })
                .collect(Collectors.toList());

        return new OrderResponse(
                savedOrder.getId(),
                savedOrder.getOrderDate(),
                savedOrder.getTotalAmount(),
                savedOrder.getStatus(),
                productDTOList
        );
    }

    @Override
    public OrderResponse getOrderById(Long id) {
       return orderRepository.findById(id).orElseThrow().toResponse();

    }

    @Override
    public List<OrderResponse> getAllOrdersByCustomerId(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow();
        List<Order> orders = orderRepository.findAllByCustomer(customer);
        return orders.stream().map(Order::toResponse).collect(Collectors.toList());

    }

    @Override
    public OrderResponse updateOrderById(Long id, Status status) {
        Order order = orderRepository.findById(id).orElseThrow();
        order.setStatus(status);
        Order order1 = orderRepository.save(order);
        return order1.toResponse();
    }
}
