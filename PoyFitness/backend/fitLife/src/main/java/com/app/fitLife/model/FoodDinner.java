package com.app.fitLife.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "food_dinner")
public class FoodDinner extends Food{

    public FoodDinner(Long id, String name, Double calories) {
        super(id, name, calories);
    }

    @ManyToOne
    @JoinColumn(name = "diet_id", nullable = false)
    @JsonBackReference
    private Diets diet;

    @ManyToMany
    @JoinTable(
            name = "food_dinner_products",
            joinColumns = @JoinColumn(name = "food_dinner_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Products> products;

}

