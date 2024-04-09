package org.example.thelooker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.thelooker.model.Category;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class ProductDto {
    private Long id;
    private String description;
    private String image;
    private String name;
    private Integer price;
    private Integer quantity;
    private Integer weight;
    private Category category;
}

