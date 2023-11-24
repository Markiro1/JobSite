package com.example.jobSite.dto.user;

import com.example.jobSite.enums.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserDto {
    private Long id;

    private String nickname;

    private String email;

    private Role role;

    private boolean active;

    private String firstName;

    private String password;

    private LocalDateTime dateOfCreated;

}
