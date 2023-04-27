package com.example.helpMAMOCHKA.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "users")
@Entity
public class User extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String nickname;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "active")
    private boolean active;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.PERSIST)
    private List<Response> responses;

    @OneToOne(mappedBy = "user", cascade = CascadeType.PERSIST)
    private Profile profile;
}
