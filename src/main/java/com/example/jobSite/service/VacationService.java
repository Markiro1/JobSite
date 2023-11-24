package com.example.jobSite.service;

import com.example.jobSite.dto.vacation.VacationDto;
import com.example.jobSite.entity.Vacation;

import java.util.List;

public interface VacationService {
    Vacation save(VacationDto vacationDto);
    VacationDto findById(Long id);
    void deleteById(Long id);
    List<VacationDto> getAllVacations();
}
