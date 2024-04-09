package org.example.thelooker.service;

import org.example.thelooker.dto.CartDto;

public interface CartService {
    CartDto addProductToCart(Long customerId, Long productId, int quantity);
    CartDto getCartForCustomer(Long customerId);
    void removeProductFromCart(Long customerId, Long productId);
    void clearCart(Long customerId);


}
