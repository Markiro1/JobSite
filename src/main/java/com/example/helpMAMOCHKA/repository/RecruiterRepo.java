package com.example.helpMAMOCHKA.repository;

import com.example.helpMAMOCHKA.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecruiterRepo extends JpaRepository<Recruiter, Long> {
    Optional<Recruiter> findByEmail(String email);
    List<Recruiter> findAll();
    Recruiter findByFullName(String username);
}
