package org.example.spring_data_jpa_homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.spring_data_jpa_homework.model.request.CustomerRequest;
import org.example.spring_data_jpa_homework.model.response.ApiResponse;
import org.example.spring_data_jpa_homework.model.response.CustomerResponse;
import org.example.spring_data_jpa_homework.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@Data
@AllArgsConstructor
@RequestMapping("/api/v1/custoomers")
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    @Operation(summary = "Create customer")
    public ResponseEntity <?> createCustomer (@RequestBody CustomerRequest customerRequest){
        CustomerResponse customer = customerService.createCustomer(customerRequest);
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.CREATED)
                .message("Customer created successfully")
                .payload(customer)
                .build();
        return ResponseEntity.ok(apiResponse);

    }
    @GetMapping
    @Operation(summary = "get all customers")
    public ResponseEntity<ApiResponse<List<CustomerResponse>>> getAllCustomer (
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
    List<CustomerResponse>  customerPage = customerService.getAllCustomer(page, size, sortBy, direction);
//    return ResponseEntity.ok(customerService.getAllCustomer(page, size, sortBy, direction));
        ApiResponse<List<CustomerResponse>> apiResponse = ApiResponse.<List<CustomerResponse>>builder()
                .status(HttpStatus.OK)
                .message("get all customer successfully")
                .payload(customerPage)
                .build();
       return ResponseEntity.status(HttpStatus.OK).body(apiResponse);

    }

    @GetMapping("/{id}")
    @Operation(summary = "Fetch customer by id")
    public ResponseEntity<ApiResponse<CustomerResponse>> getCustomerById (@PathVariable Long id){
        CustomerResponse customerResponse = customerService.getCustomerById(id);
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
               .status(HttpStatus.OK)
               .message("get customer by id successfully")
               .payload(customerResponse)
               .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete customer by id")
    public ResponseEntity<ApiResponse<CustomerResponse>> deleteCustomerById (@PathVariable Long id){
        customerService.deleteCustomerById(id);
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.OK)
                .message("Customer id " + id + " deleted successfully")
                .build();
        return ResponseEntity.ok(apiResponse);

    }
    @PutMapping("/{id}")
    @Operation(summary = "Update customer by id")
    public ResponseEntity<ApiResponse<CustomerResponse>> updateCustomerById (@PathVariable Long id, @RequestBody CustomerRequest customerRequest){
        CustomerResponse customerResponse = customerService.updateCustomerById(id, customerRequest);
        ApiResponse<CustomerResponse> apiResponse = ApiResponse.<CustomerResponse>builder()
                .status(HttpStatus.OK)
                .message("get customer by id successfully")
                .payload(customerResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
