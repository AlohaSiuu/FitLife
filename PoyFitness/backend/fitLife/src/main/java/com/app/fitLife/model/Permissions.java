package com.app.fitLife.model;

import com.app.fitLife.utils.PermissionsEnum;
import jakarta.persistence.*;
import lombok.*;

//Anotaciones lombok
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "permissions")
public class Permissions {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(unique = true, nullable = false, updatable = false, length = 20)
    private PermissionsEnum permissionsEnum;
}
