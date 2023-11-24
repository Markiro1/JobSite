package com.example.jobSite.controller;

import com.example.jobSite.dto.securityDto.SignInDto;
import com.example.jobSite.dto.securityDto.SignUpDto;
import com.example.jobSite.dto.securityDto.SuccessSignInDto;
import com.example.jobSite.dto.securityDto.SuccessSignUpDto;
import com.example.jobSite.service.SecurityService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/security")
@Validated
@Slf4j
public class SecurityController {
    private final SecurityService service;

    @Autowired
    public SecurityController(SecurityService service) {
        this.service = service;
    }

    @PostMapping("/signUp")
    public ResponseEntity<SuccessSignUpDto> signUp(@Valid @RequestBody SignUpDto dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.signUp(dto));
    }

    @PostMapping("/signIn")
    public SuccessSignInDto signIn(@Valid @RequestBody SignInDto dto) {
        return service.signIn(dto);
    }
}