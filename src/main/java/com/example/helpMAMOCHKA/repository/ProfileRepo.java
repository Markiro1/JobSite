package com.example.helpMAMOCHKA.repository;

import com.example.helpMAMOCHKA.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProfileRepo extends JpaRepository<Profile, Long> {
    @Query("SELECT p FROM Profile p")
    List<Profile> getAll();
}
