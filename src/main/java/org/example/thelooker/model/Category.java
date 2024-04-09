package org.example.thelooker.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "category", schema = "ecommerce")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_gen")
    @SequenceGenerator(name = "category_seq_gen", sequenceName = "CATEGORY_SEQ", allocationSize=1)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "name")
    private String name;

}
