package org.example.thelooker.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.thelooker.model.CartProduct;
import org.example.thelooker.model.Customer;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CartDto {
    private Long id;
    private Customer customer;
    private Set<CartProduct> products;
}

