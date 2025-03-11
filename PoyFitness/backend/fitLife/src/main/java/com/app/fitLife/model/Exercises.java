package com.app.fitLife.model;

import com.app.fitLife.utils.TypeExercise;
import jakarta.persistence.*;
import lombok.*;

import java.lang.reflect.Type;

//Anotaciones lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "exercises")
public class Exercises {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column
    private TypeExercise type;

    @Column
    private String description;

    @Column
    private String video;

    @ManyToOne
    @JoinColumn(name = "rountine_id")
    private Routines routine;
}
