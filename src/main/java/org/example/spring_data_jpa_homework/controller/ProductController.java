package org.example.spring_data_jpa_homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.spring_data_jpa_homework.model.request.ProductRequest;
import org.example.spring_data_jpa_homework.model.response.ApiResponse;
import org.example.spring_data_jpa_homework.model.response.ProductResponse;
import org.example.spring_data_jpa_homework.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@AllArgsConstructor
@Data
@RequestMapping("/api/v1/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @Operation(summary = "Create a new product")
    public ResponseEntity<ApiResponse<ProductResponse>> createProduct(@RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.createProduct(productRequest);
        ApiResponse<ProductResponse> apiResponse = ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.CREATED)
                .message("A new product is inserted successfully.")
                .payload(productResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @GetMapping
    @Operation(summary = "Get all products")
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAllProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction
    ){
        List<ProductResponse> productPage = productService.getAllProducts(page, size, sortBy, direction);
        ApiResponse<List<ProductResponse>> apiResponse = ApiResponse.<List<ProductResponse>>builder()
                .status(HttpStatus.OK)
                .message("Get all products successfully.")
                .payload(productPage)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get a product by id")
    public ResponseEntity<ApiResponse<ProductResponse>> getProductById(@PathVariable Long id){
        ProductResponse productResponse = productService.getProductById(id);
        ApiResponse <ProductResponse> apiResponse = ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.OK)
                .message("Get product with id " + id + " successfully")
                .payload(productResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a product by id")
    public ResponseEntity<ApiResponse<ProductResponse>> deleteProductById(@PathVariable Long id){
         productService.deleteProductById(id);
        ApiResponse <ProductResponse> apiResponse = ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.OK)
                .message("Delete product with id " + id + " successfully")
                .build();
        return ResponseEntity.ok(apiResponse);
    }
    @PutMapping("/{id}")
    @Operation(summary = "Update a product by id")
    public ResponseEntity<ApiResponse<ProductResponse>> updateProductById(@PathVariable Long id, @RequestBody ProductRequest productRequest){
        ProductResponse productResponse =productService.updateProductById(id,productRequest);
        ApiResponse <ProductResponse> apiResponse = ApiResponse.<ProductResponse>builder()
                .status(HttpStatus.OK)
                .message("Update product with id " + id + " successfully")
                .payload(productResponse)
                .build();
        return ResponseEntity.ok(apiResponse);
    }

}
