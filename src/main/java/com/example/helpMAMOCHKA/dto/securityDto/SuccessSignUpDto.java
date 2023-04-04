package com.example.helpMAMOCHKA.dto.securityDto;

import lombok.*;

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
