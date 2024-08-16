package org.example.spring_data_jpa_homework.service.serviceImpl;

import org.example.spring_data_jpa_homework.model.entity.Product;
import org.example.spring_data_jpa_homework.model.request.ProductRequest;
import org.example.spring_data_jpa_homework.model.response.ProductResponse;
import org.example.spring_data_jpa_homework.repository.ProductRepository;
import org.example.spring_data_jpa_homework.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse createProduct(ProductRequest productRequest) {
//        return productRepository.save(productRequest.toEntity());
        Product product = Product.builder()
                .ProductName(productRequest.getProductName())
                .UnitPrice(productRequest.getUnitPrice())
                .Description(productRequest.getDescription())
                .build();
        return productRepository.save(product).toResponse();

    }

    @Override
    public List<ProductResponse> getAllProducts(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(page-1, size,sort);
        Page <Product> products = productRepository.findAll(pageable);
        return products.getContent().stream().map(Product::toResponse).toList();

    }

    @Override
    public ProductResponse getProductById(Long id) {
        return productRepository.findById(id).orElseThrow().toResponse();
    }

    @Override
    public void deleteProductById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponse updateProductById(Long id, ProductRequest productRequest) {
        Product product = productRepository.findById(id).orElseThrow();
        product.setProductName(productRequest.getProductName());
        product.setUnitPrice(productRequest.getUnitPrice());
        product.setDescription(productRequest.getDescription());
        return productRepository.save(product).toResponse();
    }
}
