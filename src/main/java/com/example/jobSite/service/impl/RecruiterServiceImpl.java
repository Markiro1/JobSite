package com.example.jobSite.service.impl;

import com.example.jobSite.dto.recruiter.RecruiterDto;
import com.example.jobSite.dto.recruiter.RecruiterWithoutPasswordDto;
import com.example.jobSite.entity.Recruiter;
import com.example.jobSite.repository.RecruiterRepo;
import com.example.jobSite.service.RecruiterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecruiterServiceImpl implements RecruiterService {
    private final RecruiterRepo recruiterRepo;
    private final ModelMapper modelMapper;

    @Override
    public RecruiterDto save(RecruiterDto dto) {
        Recruiter recruiter = modelMapper.map(dto, Recruiter.class);
        return modelMapper.map(recruiterRepo.save(recruiter), RecruiterDto.class);
    }

    @Override
    public RecruiterDto findByEmail(String email) {
        Optional<Recruiter> optionalRecruiter = recruiterRepo.findByEmail(email);
        return optionalRecruiter.isEmpty() ? null : modelMapper.map(optionalRecruiter.get(), RecruiterDto.class);
    }

    @Override
    public RecruiterWithoutPasswordDto findById(Long id) {
        Recruiter recruiter = recruiterRepo.findById(id)
                .orElseThrow();
        return modelMapper.map(recruiter, RecruiterWithoutPasswordDto.class);
    }

    @Override
    public void deleteById(Long id) {
        RecruiterWithoutPasswordDto dto = findById(id);
        recruiterRepo.delete(modelMapper.map(dto, Recruiter.class));
    }

    @Override
    public List<RecruiterWithoutPasswordDto> getAllRecruiters() {
        return modelMapper.map(recruiterRepo.findAll(), new TypeToken<List<RecruiterWithoutPasswordDto>>(){}.getType());
    }
}
