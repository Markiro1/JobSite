package com.example.helpMAMOCHKA.entity;

import com.example.helpMAMOCHKA.enums.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.List;

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

    @OneToMany(mappedBy = "vacation", cascade = CascadeType.PERSIST)
    private List<FeedBack> feedBacks;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "recruiter_id")
    private Recruiter recruiter;
}
