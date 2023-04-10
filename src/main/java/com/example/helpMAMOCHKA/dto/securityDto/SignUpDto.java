package com.example.helpMAMOCHKA.dto.securityDto;

import com.example.helpMAMOCHKA.enums.Role;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpDto {
    private String nickname;
    private String firstName;
    private String email;
    private String password;
    private Role role;
}
