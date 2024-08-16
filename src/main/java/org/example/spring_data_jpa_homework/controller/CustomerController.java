package org.example.spring_data_jpa_homework.controller;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.spring_data_jpa_homework.model.Customer;
import org.example.spring_data_jpa_homework.model.request.CustomerRequest;
import org.example.spring_data_jpa_homework.model.response.ApiResponse;
import org.example.spring_data_jpa_homework.model.response.CustomerResponse;
import org.example.spring_data_jpa_homework.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("/api/v1/custoomers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity <?> createCustomer (@RequestBody CustomerRequest customerRequest){
        CustomerResponse customer = customerService.createCustomer(customerRequest);
        ApiResponse apiResponse = ApiResponse.builder()
                .message("Customer created successfully")
                .payload(customer)
                .statusCode(201)
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(apiResponse);

    }
}
