package com.example.helpMAMOCHKA.dto.user;

import com.example.helpMAMOCHKA.enums.Role;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class UserDtoWithoutPassword {
    private Long id;

    private String nickname;

    private String email;

    private Role role;

    private boolean active;

    private String firstName;

    private LocalDateTime dateOfCreated;
}
