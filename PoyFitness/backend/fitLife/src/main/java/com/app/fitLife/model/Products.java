package com.app.fitLife.model;


import jakarta.persistence.*;
import lombok.*;

//Anotaciones lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "products")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    private Double calories;

    private Boolean vegan;
}
