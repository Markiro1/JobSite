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
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class VacationServiceImpl implements VacationService {
    private final VacationRepo vacationRepo;
    private final RecruiterRepo recruiterRepo;
    private final ModelMapper modelMapper;

    @Override
    public Vacation save(VacationDto vacationDto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        Recruiter recruiter = recruiterRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Recruiter not found for email: " + email));

        Vacation vacation = createVacation(vacationDto);
        vacation.setRecruiter(recruiter);

        Vacation savedVacation = vacationRepo.save(vacation);
        vacation.setId(savedVacation.getId());

        if (recruiter != null) {
            recruiter.getVacation().add(vacation);
            recruiterRepo.save(recruiter);
        }

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
                .english_level(dto.getEnglish_level())
                .specialization(dto.getSpecialization())
                .responses(dto.getResponses())
                .build();
    }

}
