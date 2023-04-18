package com.example.helpMAMOCHKA.dto.vacation;

import com.example.helpMAMOCHKA.entity.FeedBack;
import com.example.helpMAMOCHKA.enums.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@EqualsAndHashCode
public class VacationDto {
    private Long id;

    private String required_skills;

    private JobDomain jobDomain;

    private String description;

    private String salary;

    private Remote remote;

    private Country country;

    private Experience experience;

    private English english;

    private Specialization specialization;

    private List<FeedBack> feedBacks;

}
