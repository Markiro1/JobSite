package com.example.helpMAMOCHKA.service.impl;

import com.example.helpMAMOCHKA.dto.vacation.VacationDto;
import com.example.helpMAMOCHKA.entity.Recruiter;
import com.example.helpMAMOCHKA.entity.Vacation;
import com.example.helpMAMOCHKA.repository.RecruiterRepo;
import com.example.helpMAMOCHKA.repository.VacationRepo;
import com.example.helpMAMOCHKA.service.VacationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService {
    private final VacationRepo vacationRepo;
    private final RecruiterRepo recruiterRepo;
    private final ModelMapper modelMapper;

    @Override
    public Vacation save(VacationDto vacationDto) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Recruiter recruiter = recruiterRepo.findByFullName(auth.getName());
        Vacation vacation = createVacation(vacationDto);
        Vacation savedVacation = vacationRepo.save(vacation);
        vacation.setId(savedVacation.getId());
        vacation.setRecruiter(recruiter);

        return vacation;
    }

    @Override
    public VacationDto findById(Long id) {
        Vacation vacation = vacationRepo.findById(id)
                .orElseThrow();
        return modelMapper.map(vacation, VacationDto.class);
    }

    @Override
    public void deleteById(Long id) {
        VacationDto vacationDto = findById(id);
        vacationRepo.delete(modelMapper.map(vacationDto, Vacation.class));
    }

    @Override
    public List<VacationDto> getAllVacations() {
        return modelMapper.map(vacationRepo.findAll(), new TypeToken<List<VacationDto>>(){}.getType());
    }

    private Vacation createVacation(VacationDto dto) {
        return Vacation.builder()
                .required_skills(dto.getRequired_skills())
                .jobDomain(dto.getJobDomain())
                .description(dto.getDescription())
                .salary(dto.getSalary())
                .remote(dto.getRemote())
                .country(dto.getCountry())
                .experience(dto.getExperience())
                .english(dto.getEnglish())
                .specialization(dto.getSpecialization())
                .feedBacks(dto.getFeedBacks())
                .build();
    }

}
