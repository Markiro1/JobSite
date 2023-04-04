package com.example.helpMAMOCHKA.repository;

import com.example.helpMAMOCHKA.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByEmail(String email);

    List<User> findAll();

}
