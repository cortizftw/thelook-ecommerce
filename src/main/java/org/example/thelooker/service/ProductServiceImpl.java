package org.example.thelooker.service;

import org.example.thelooker.dto.ProductDto;
import org.example.thelooker.model.Product;
import org.example.thelooker.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    private ProductDto convertToDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setDescription(product.getDescription());
        productDto.setImage(product.getImage());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setQuantity(product.getQuantity());
        productDto.setWeight(product.getWeight());
        productDto.setCategory(product.getCategory());
        return productDto;
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
