package com.app.fitLife.model;

import com.app.fitLife.utils.RoleEnum;
import com.app.fitLife.utils.TypeDiet;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//Anotaciones lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "diets")
public class Diets {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private TypeDiet type;

    @Column
    private String description;


    @Column
    private int total_calories;

    @OneToMany(mappedBy = "diet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FoodMorning> foodMornings;

    @OneToMany(mappedBy = "diet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FoodLaunch> foodLaunches;

    @OneToMany(mappedBy = "diet", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<FoodDinner> foodDinners;

    @ManyToMany(mappedBy = "diets")
    private List<UserEntity> userEntities;


}
