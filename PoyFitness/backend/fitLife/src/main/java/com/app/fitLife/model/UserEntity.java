    package com.app.fitLife.model;

    import jakarta.persistence.*;
    import lombok.*;

    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;

    //Anotaciones lombok
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder

    @Entity
    @Table(name = "users")
    public class UserEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, length = 100)
        private String username;

        @Column(nullable = false)
        private String email;

        @Column(nullable = false)
        private String password;

        @Column
        private int age;

        @Column
        private Double weight;

        @Column
        private Double height;

        @OneToOne
        @JoinColumn(name = "tracking_id", nullable = true)
        private UserTracking trakings;

        @OneToOne
        @JoinColumn(name = "point_id", nullable = true)
        private PointSystem pointSystem;

        @Column(name = "is_enabled")
        private boolean isEnabled;

        @Column(name = "account_No_Expired")
        private boolean accountNoExpired;

        @Column(name = "account_No_locked")
        private boolean accountNoLocked;

        @Column(name = "credential_No_Expired")
        private boolean credentialNoExpired;

        @ManyToMany
        @JoinTable(
                name = "user_diets",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "diet_id")
        )
        private List<Diets> diets;

        @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        @JoinTable(
                name = "user_roles",
                joinColumns = @JoinColumn(name = "user_id"),
                inverseJoinColumns = @JoinColumn(name = "role_id")
        )
        private Set<RoleEntity> roles = new HashSet<>();

    }
