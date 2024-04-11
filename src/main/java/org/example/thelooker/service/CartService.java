package org.example.thelooker.service;

import org.example.thelooker.model.Cart;
import org.example.thelooker.model.CartProduct;
import org.example.thelooker.model.Customer;
import org.example.thelooker.model.Product;
import org.example.thelooker.repository.CartProductRepository;
import org.example.thelooker.repository.CartRepository;
import org.example.thelooker.repository.CustomerRepository;
import org.example.thelooker.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartProductRepository cartProductRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public void addToCart(Long customerId, Long productId, int qty) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Product not found"));

        Cart cart = cartRepository.findByCustomer_Id(customerId)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);
                    return cartRepository.save(newCart);
                });

        Optional<CartProduct> existingProduct = cart.getProducts().stream()
                .filter(p -> p.getProduct().getId().equals(productId))
                .findFirst();

        if (existingProduct.isPresent()) {
            CartProduct cartProduct = existingProduct.get();
            cartProduct.setQuantity(cartProduct.getQuantity() + qty);
            cartProductRepository.save(cartProduct);
        } else {
            CartProduct newCartProduct = new CartProduct();
            newCartProduct.setCart(cart);
            newCartProduct.setProduct(product);
            newCartProduct.setQuantity(qty);
            cartProductRepository.save(newCartProduct);
        }
    }

    public List<CartProduct> getCartProductsByCustomerId(Long customerId) {
        return cartRepository.findByCustomer_Id(customerId)
                .map(cart -> new ArrayList<>(cart.getProducts()))
                .orElse(new ArrayList<>());
    }
}
