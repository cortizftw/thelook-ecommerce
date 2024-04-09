package org.example.thelooker.service;

import org.example.thelooker.dto.CartDto;
import org.example.thelooker.model.Cart;
import org.example.thelooker.model.CartProduct;
import org.example.thelooker.model.Customer;
import org.example.thelooker.model.Product;
import org.example.thelooker.repository.CartProductRepository;
import org.example.thelooker.repository.CartRepository;
import org.example.thelooker.repository.CustomerRepository;
import org.example.thelooker.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartProductRepository cartProductRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    public CartServiceImpl(CartRepository cartRepository, CartProductRepository cartProductRepository,
                           CustomerRepository customerRepository, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartProductRepository = cartProductRepository;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public static CartDto addProductToCart(Long customerId, Long productId, int quantity) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<Cart> cartOptional = cartRepository.findByCustomerId(customerId);
        Cart cart = cartOptional.orElseGet(() -> createCartForCustomer(customer));

        CartProduct cartProduct = new CartProduct();
        cartProduct.setCart(cart);
        cartProduct.setProduct(product);
        cartProduct.setQuantity(quantity); // Assuming quantity is handled here

        cartProductRepository.save(cartProduct);

        return convertToDto(cart); // Assuming you have a method to convert a Cart entity to CartDto
    }

    private Cart createCartForCustomer(Customer customer) {
        Cart newCart = new Cart();
        newCart.setCustomer(customer);
        return cartRepository.save(newCart);
    }

    // Implement other methods...

    private CartDto convertToDto(Cart cart) {
        // Implement conversion logic...
        return new CartDto();
    }

    @Override
    public CartDto getCartForCustomer(Long customerId) {
        Optional<Cart> cartOptional = cartRepository.findByCustomerId(customerId);
        Cart cart = cartOptional.orElseThrow(() -> new RuntimeException("Cart not found"));
        return convertToDto(cart);
    }

    @Override
    public void removeProductFromCart(Long customerId, Long productId) {
        Optional<Cart> cartOptional = cartRepository.findByCustomerId(customerId);
        Cart cart = cartOptional.orElseThrow(() -> new RuntimeException("Cart not found"));

        cartProductRepository.deleteByCartIdAndProductId(cart.getId(), productId);
    }

    @Override
    public void clearCart(Long customerId) {
        Optional<Cart> cartOptional = cartRepository.findByCustomerId(customerId);
        Cart cart = cartOptional.orElseThrow(() -> new RuntimeException("Cart not found"));

        cartProductRepository.deleteByCartId(cart.getId());
    }


}
