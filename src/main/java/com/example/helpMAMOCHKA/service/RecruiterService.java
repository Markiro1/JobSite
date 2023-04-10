package com.example.helpMAMOCHKA.service;

import com.example.helpMAMOCHKA.dto.recruiter.RecruiterDto;
import com.example.helpMAMOCHKA.dto.recruiter.RecruiterWithoutPasswordDto;

import java.util.List;

public interface RecruiterService {
    RecruiterDto save(RecruiterDto dto);
    RecruiterDto findByEmail(String email);
    RecruiterWithoutPasswordDto findById(Long id);
    void deleteById(Long id);
    List<RecruiterWithoutPasswordDto> getAllRecruiters();
}
