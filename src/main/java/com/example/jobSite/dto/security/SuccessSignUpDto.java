package com.example.jobSite.dto.securityDto;

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
public class SuccessSignUpDto {
    private Long userId;
    private String nickName;
    private String email;
}