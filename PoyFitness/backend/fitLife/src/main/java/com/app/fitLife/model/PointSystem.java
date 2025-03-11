package com.app.fitLife.model;

import jakarta.persistence.*;
import lombok.*;

//Anotaciones lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "point_system")
public class PointSystem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer accumulated_points;

    @Column(columnDefinition = "TEXT")
    private String available_rewards;

    @OneToOne
    private UserEntity userEntity;
}
