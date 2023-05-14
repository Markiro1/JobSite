package com.example.helpMAMOCHKA.service;

import com.example.helpMAMOCHKA.dto.securityDto.SignInDto;
import com.example.helpMAMOCHKA.dto.securityDto.SignUpDto;
import com.example.helpMAMOCHKA.dto.securityDto.SuccessSignInDto;
import com.example.helpMAMOCHKA.dto.securityDto.SuccessSignUpDto;

public interface SecurityService {

    SuccessSignUpDto signUp(SignUpDto dto);
    SuccessSignInDto signIn(SignInDto dto);
}