package com.example.helpMAMOCHKA.dto.securityDto;

import com.example.helpMAMOCHKA.enums.Role;
import lombok.*;

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
