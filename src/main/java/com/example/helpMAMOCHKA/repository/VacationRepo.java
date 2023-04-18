package com.example.helpMAMOCHKA.repository;

import com.example.helpMAMOCHKA.entity.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VacationRepo extends JpaRepository<Vacation, Long> {
    List<Vacation> findAll();
}
