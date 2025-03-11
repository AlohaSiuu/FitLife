package com.app.fitLife.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

//Anotaciones lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "challenges")
public class Challenges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column
    private int duratiom;


    @ManyToMany(cascade =  CascadeType.ALL)
    @JoinTable(
            name = "user_challenges",
            joinColumns = @JoinColumn(name = "challenge_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> parcitipatingUserEntities;
}
