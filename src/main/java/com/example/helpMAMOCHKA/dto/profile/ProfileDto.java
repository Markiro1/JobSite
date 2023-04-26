package com.example.helpMAMOCHKA.dto.profile;

import com.example.helpMAMOCHKA.enums.English;
import com.example.helpMAMOCHKA.enums.Position;
import com.example.helpMAMOCHKA.enums.Specialization;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class ProfileDto {

    private Long id;

    private Specialization specialization;

    private int experience;

    private String expected_salary;

    private Position position;

    private English english_level;
}
