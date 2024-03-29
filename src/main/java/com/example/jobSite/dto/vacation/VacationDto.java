package com.example.jobSite.dto.vacation;

import com.example.jobSite.entity.Response;
import com.example.jobSite.enums.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    private English english_level;

    private Specialization specialization;

    private List<Response> responses;
}
