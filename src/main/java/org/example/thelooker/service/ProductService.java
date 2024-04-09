package org.example.thelooker.service;

import org.example.thelooker.dto.ProductDto;

import java.util.List;

public interface ProductService {
    void deleteProduct(Long id);
    void updateProduct(ProductDto productDto);
    void createProduct(ProductDto productDto);
    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
    ProductDto getProductByName(String name);
}
