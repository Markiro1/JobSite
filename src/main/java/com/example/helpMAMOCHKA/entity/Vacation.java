package com.example.helpMAMOCHKA.entity;

import com.example.helpMAMOCHKA.enums.*;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "vacation")
@Entity
public class Vacation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String required_skills;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private JobDomain jobDomain;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String salary;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Remote remote;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Country country;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Experience experience;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private English english;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private Specialization specialization;

}
