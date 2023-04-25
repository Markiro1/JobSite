package com.example.helpMAMOCHKA.dto.securityDto;

import com.example.helpMAMOCHKA.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignInDto {
    private String email;
    private String password;

    private Role role;
}
