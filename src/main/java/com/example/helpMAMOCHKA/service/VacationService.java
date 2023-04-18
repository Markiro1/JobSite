package com.example.helpMAMOCHKA.service;

import com.example.helpMAMOCHKA.dto.vacation.VacationDto;
import com.example.helpMAMOCHKA.entity.Vacation;

import java.util.List;

public interface VacationService {
    Vacation save(VacationDto vacationDto);
    VacationDto findById(Long id);
    void deleteById(Long id);
    List<VacationDto> getAllVacations();
}
