package com.example.jobSite.dto.auth;

import lombok.Data;

@Data
public class AuthenticationRequestDto {

    String email;

    String password;
}
