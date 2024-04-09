package org.example.thelooker.repository;

import org.example.thelooker.model.CartProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartProductRepository extends JpaRepository<CartProduct, Long> {
    List<CartProduct> findByCartId(Long cartId);

    void deleteByCartIdAndProductId(Long id, Long productId);

    void deleteByCartId(Long id);
}
