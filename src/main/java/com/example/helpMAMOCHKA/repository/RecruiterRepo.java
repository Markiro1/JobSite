package com.example.helpMAMOCHKA.repository;

import com.example.helpMAMOCHKA.entity.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecruiterRepo extends JpaRepository<Recruiter, Long> {
    Recruiter findByEmail(String email);
    List<Recruiter> findAll();
}
