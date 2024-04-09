package org.example.thelooker.dto;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.thelooker.model.Cart;
import org.example.thelooker.model.Product;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartProductDto {
    private Long id;
    private Cart cart;
    private Product product;
    private int quantity;
}

