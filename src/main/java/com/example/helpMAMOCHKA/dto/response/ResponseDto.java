package com.example.helpMAMOCHKA.dto.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ResponseDto {
    private Long id;

    private String message;
}
