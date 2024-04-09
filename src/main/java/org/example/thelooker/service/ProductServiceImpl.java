package org.example.thelooker.service;

import org.example.thelooker.dto.ProductDto;
import org.example.thelooker.model.Product;
import org.example.thelooker.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void updateProduct(ProductDto productDto) {
        Product product = productRepository.findById(productDto.getId()).orElseThrow(() -> new RuntimeException("Product not found"));
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setWeight(productDto.getWeight());
        product.setCategory(productDto.getCategory());
        productRepository.save(product);
    }

    @Override
    public void createProduct(ProductDto productDto) {
        Product product = new Product();
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setQuantity(productDto.getQuantity());
        product.setWeight(productDto.getWeight());
        product.setCategory(productDto.getCategory());
        productRepository.save(product);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        return null;
    }

    @Override
    public ProductDto getProductById(Long id) {
        return null;
    }

    @Override
    public ProductDto getProductByName(String name) {
        return null;
    }


}
