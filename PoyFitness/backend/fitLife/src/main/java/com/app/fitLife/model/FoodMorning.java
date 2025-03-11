package com.app.fitLife.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "food_morning")  // Nombre de la tabla en la BD
public class FoodMorning extends Food {

    public FoodMorning(Long id, String name, Double calories) {
        super(id, name, calories);
    }

    @ManyToOne
    @JoinColumn(name = "diet_id", nullable = false)
    @JsonBackReference
    private Diets diet;


    @ManyToMany
    @JoinTable(
            name = "food_morning_products",
            joinColumns = @JoinColumn(name = "food_morning_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Products> products;

}


