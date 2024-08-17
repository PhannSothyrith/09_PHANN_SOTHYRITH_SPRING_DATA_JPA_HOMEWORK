package org.example.spring_data_jpa_homework.service;

import org.example.spring_data_jpa_homework.model.request.ProductRequest;
import org.example.spring_data_jpa_homework.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProduct(ProductRequest productRequest);
    List<ProductResponse> getAllProducts (int page,int size,String sort,String direction);
    ProductResponse getProductById(Long id);
    void deleteProductById(Long id);
    ProductResponse updateProductById(Long id,ProductRequest productRequest);

}
