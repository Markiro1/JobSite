package com.example.jobSite.service;

import com.example.jobSite.dto.recruiter.RecruiterDto;
import com.example.jobSite.dto.recruiter.RecruiterWithoutPasswordDto;

import java.util.List;

public interface RecruiterService {
    RecruiterDto save(RecruiterDto dto);
    RecruiterDto findByEmail(String email);
    RecruiterWithoutPasswordDto findById(Long id);
    void deleteById(Long id);
    List<RecruiterWithoutPasswordDto> getAllRecruiters();
}
