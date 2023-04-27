package com.example.helpMAMOCHKA.repository;

import com.example.helpMAMOCHKA.entity.Response;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResponseRepo extends JpaRepository<Response, Long> {
    List<Response> findAll();
}
