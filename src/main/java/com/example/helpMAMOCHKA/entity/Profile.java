package com.example.helpMAMOCHKA.entity;

import com.example.helpMAMOCHKA.enums.English;
import com.example.helpMAMOCHKA.enums.Position;
import com.example.helpMAMOCHKA.enums.Specialization;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "profile")
@Entity
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Specialization specialization;

    @Column(nullable = false)
    private int experience;

    @Column(nullable = false)
    private String expected_salary;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Position position;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private English english_level;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
