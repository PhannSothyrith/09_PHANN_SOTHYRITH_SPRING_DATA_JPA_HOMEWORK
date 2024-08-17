package org.example.spring_data_jpa_homework.service;

import org.example.spring_data_jpa_homework.model.enums.Status;
import org.example.spring_data_jpa_homework.model.request.OrderRequest;
import org.example.spring_data_jpa_homework.model.response.OrderResponse;

import java.util.List;

public interface OrderService {
    OrderResponse createOrder(Long customerId, List<OrderRequest> orderRequest);
    OrderResponse getOrderById (Long id);
    List<OrderResponse> getAllOrdersByCustomerId (Long customerId);
    OrderResponse updateOrderById(Long id , Status status);
}
