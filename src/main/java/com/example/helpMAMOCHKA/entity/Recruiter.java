package com.example.helpMAMOCHKA.entity;

import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
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
@Table(name = "recruiters")
@Entity
public class Recruiter extends BaseEntity {

    @Column(nullable = false, length = 30)
    private String fullName;

    private String position;

    private String company;

    @Column(name = "active")
    private boolean active;

    private String number;

    private String telegram;

    private LocalDateTime dateOfCreated;

    @PrePersist
    private void init() {
        dateOfCreated = LocalDateTime.now();
    }

    @OneToMany(mappedBy = "recruiter", cascade = CascadeType.PERSIST)
    private List<Vacation> vacation;
}
