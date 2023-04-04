package com.example.helpMAMOCHKA.dto.securityDto;

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
}
