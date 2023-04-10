package com.example.helpMAMOCHKA.dto.recruiter;

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
public class RecruiterDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String position;

    private String company;

    private boolean active;

    private String number;

    private String telegram;

    private String password;

    private Role role;

    private LocalDateTime dateOfCreated;
}
