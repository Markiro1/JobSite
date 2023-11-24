package com.example.jobSite.dto.securityDto;

import com.example.jobSite.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;

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
