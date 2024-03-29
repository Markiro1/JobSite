package com.example.jobSite.controller;

import com.example.jobSite.dto.vacation.VacationDto;
import com.example.jobSite.entity.Vacation;
import com.example.jobSite.service.VacationService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/vacation")
@AllArgsConstructor
@Validated
public class VacationController {
    private final VacationService vacationService;

    @PostMapping("/createVacation")
    public ResponseEntity<Vacation> signUp(@Valid @RequestBody VacationDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vacationService.save(dto));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<VacationDto> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(vacationService.findById(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Object> deleteVacationById(@PathVariable(value = "id") Long userId) {
        vacationService.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<VacationDto>> getAllVacations() {
        return ResponseEntity.status(HttpStatus.OK).body(vacationService.getAllVacations());
    }
}
