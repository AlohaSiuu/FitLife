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
@Table(name = "food_launch")
public class FoodLaunch extends Food{
    public FoodLaunch(Long id, String name, Double calories) {
        super(id, name, calories);
    }

    @ManyToOne
    @JoinColumn(name = "diet_id", nullable = false)
    @JsonBackReference
    private Diets diet;

    @ManyToMany
    @JoinTable(
            name = "food_launch_products",
            joinColumns = @JoinColumn(name = "food_launch_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Products> products;
}
