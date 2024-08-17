package org.example.spring_data_jpa_homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.spring_data_jpa_homework.model.entity.Customer;
import org.example.spring_data_jpa_homework.model.enums.Status;
import org.example.spring_data_jpa_homework.model.request.OrderRequest;
import org.example.spring_data_jpa_homework.model.request.ProductRequest;
import org.example.spring_data_jpa_homework.model.response.ApiResponse;
import org.example.spring_data_jpa_homework.model.response.OrderResponse;
import org.example.spring_data_jpa_homework.model.response.ProductResponse;
import org.example.spring_data_jpa_homework.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Data
@RequestMapping("/api/v1/order")
public class OrderController {
    private final OrderService orderService;
    @PostMapping("/{customerId}")
    @Operation(summary = "Create a new order")
    public ResponseEntity<ApiResponse<OrderResponse>> createProduct(@PathVariable Long customerId, @RequestBody List<OrderRequest> orderRequest){
        OrderResponse orderResponse = orderService.createOrder(customerId ,orderRequest );
        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.CREATED)
                .message("A new order is inserted successfully.")
                .payload(orderResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get order by id")
    public ResponseEntity<ApiResponse<OrderResponse>> getOrderById(@PathVariable Long id){
        OrderResponse orderResponse = orderService.getOrderById(id);
        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.OK)
                .message("Get order id "+" is successfully")
                .payload(orderResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping("/customer/{customerId}")
    @Operation(summary = "Get all orders by customer id")
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getAllOrdersByCustomerId(@PathVariable Long customerId){
        List<OrderResponse> orderResponseList = orderService.getAllOrdersByCustomerId(customerId);
        ApiResponse<List<OrderResponse>> apiResponse = ApiResponse.<List<OrderResponse>>builder()
                .status(HttpStatus.OK)
                .message("Get all orders by customer id "+ customerId +" successfully")
                .payload(orderResponseList)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update order by id")
    public ResponseEntity<ApiResponse<OrderResponse>> updateOrder(@PathVariable Long id, @RequestParam Status status){
        OrderResponse orderResponse = orderService.updateOrderById(id, status);
        ApiResponse<OrderResponse> apiResponse = ApiResponse.<OrderResponse>builder()
                .status(HttpStatus.OK)
                .message("Update order with id " + id + " successfully")
                .payload(orderResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
