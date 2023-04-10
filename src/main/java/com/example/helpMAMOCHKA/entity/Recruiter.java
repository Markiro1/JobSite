package com.example.helpMAMOCHKA.entity;

import com.example.helpMAMOCHKA.enums.Role;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "recruiters")
@Entity
public class Recruiter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 30)
    private String fullName;

    @Column(unique = true, nullable = false, length = 50)
    private String email;

    private String position;

    private String company;

    @Column(name = "active")
    private boolean active;

    private String number;

    private String telegram;

    @Column(nullable = false, name = "password")
    private String password;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }
}
