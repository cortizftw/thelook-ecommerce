package org.example.thelooker.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "cart_product", schema = "ecommerce")
public class CartProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_product_seq_generator")
    @SequenceGenerator(name="cart_product_seq_generator", sequenceName = "CART_PRODUCT_SEQ", allocationSize=1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id")
    private Product product;

    private int quantity;
}

