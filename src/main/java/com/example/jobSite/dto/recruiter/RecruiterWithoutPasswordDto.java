package com.example.jobSite.dto.recruiter;

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
public class RecruiterWithoutPasswordDto {
    private Long id;

    private String fullName;

    private String position;

    private String company;

    private boolean active;

    private String number;

    private String telegram;

    private Role role;

    private LocalDateTime dateOfCreated;
}
