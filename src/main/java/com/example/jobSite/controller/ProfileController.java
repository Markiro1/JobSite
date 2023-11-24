package com.example.jobSite.controller;

import com.example.jobSite.dto.profile.ProfileDto;
import com.example.jobSite.entity.Profile;
import com.example.jobSite.service.ProfileService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profile")
@AllArgsConstructor
@Validated
public class ProfileController {
    private final ProfileService profileService;

    @PostMapping("/createProfile")
    public ResponseEntity<Profile> createProfile(@Valid @RequestBody ProfileDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profileService.save(dto));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ProfileDto> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.findById(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Object> deleteUserProfileById(@PathVariable(value = "id") Long userId) {
        profileService.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProfileDto>> getAllProfiles() {
        return ResponseEntity.status(HttpStatus.OK).body(profileService.getAllProfiles());
    }
}
