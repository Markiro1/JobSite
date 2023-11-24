package com.example.jobSite.service;

import com.example.jobSite.dto.securityDto.SignInDto;
import com.example.jobSite.dto.securityDto.SignUpDto;
import com.example.jobSite.dto.securityDto.SuccessSignInDto;
import com.example.jobSite.dto.securityDto.SuccessSignUpDto;

public interface SecurityService {

    SuccessSignUpDto signUp(SignUpDto dto);
    SuccessSignInDto signIn(SignInDto dto);
}