package com.app.fitLife.model;

import jakarta.persistence.*;
import lombok.*;

//Anotaciones lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder


@Entity
@Table(name = "user_tracking")
public class UserTracking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String date;

    @Column
    private Double weight;

    @Column
    private Double bim;

    @Column
    private Double calories_burned;

    @OneToOne
    private UserEntity userEntity;

}
