package com.app.fitLife.model;

import com.app.fitLife.utils.DifficultyLevel;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//Anotaciones lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "routines")
public class Routines {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column
    private String description;

    @Column
    private DifficultyLevel difficulty_level;

    @OneToMany(mappedBy = "routine")
    private List<Exercises> exercises;


}
