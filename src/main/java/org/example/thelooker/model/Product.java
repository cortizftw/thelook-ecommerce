package org.example.thelooker.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product", schema = "ecommerce")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq_gen")
    @SequenceGenerator(name = "product_seq_gen", sequenceName = "PRODUCT_SEQ", allocationSize=1)
    @Column(name = "product_id")
    private Long id;

    @NotBlank
    @Column(name = "description")
    private String description;


    @Column(name = "image")
    private String image;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "price")
    private Integer price;

    @NotNull
    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "weight")
    private Integer weight;

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "category_id")
    private Category category;


}

