package com.example.jobSite.controller;

import com.example.jobSite.dto.recruiter.RecruiterWithoutPasswordDto;
import com.example.jobSite.service.RecruiterService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recruiter")
@AllArgsConstructor
@Validated
public class RecruiterController {
    private final RecruiterService recruiterService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<RecruiterWithoutPasswordDto> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(recruiterService.findById(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Object> deleteRecruiterById(@PathVariable(value = "id") Long userId) {
        recruiterService.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<RecruiterWithoutPasswordDto>> getAllRecruiters() {
        return ResponseEntity.status(HttpStatus.OK).body(recruiterService.getAllRecruiters());
    }
}
