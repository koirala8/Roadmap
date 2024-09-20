package com.roadmap.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roadwork")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class User {

    @Id
    @Column(name = "id") // Specifies the column name for the primary key
    private Integer id;

    @Column(name = "username", nullable = false, length = 255) // Specifies the column name and constraints
    private String username;

    @Column(name = "password", nullable = false, length = 255) // Specifies the column name and constraints
    private String password;

    @Column(name = "fullName", nullable = false, length = 255) // Specifies the column name and constraints
    private String fullName;

    @Column(name = "role", nullable = false, length = 255) // Specifies the column name and constraints
    private String role;
}
