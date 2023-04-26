package com.example.helpMAMOCHKA.service.impl;

import com.example.helpMAMOCHKA.dto.profile.ProfileDto;
import com.example.helpMAMOCHKA.entity.Profile;
import com.example.helpMAMOCHKA.entity.User;
import com.example.helpMAMOCHKA.repository.ProfileRepo;
import com.example.helpMAMOCHKA.repository.UserRepo;
import com.example.helpMAMOCHKA.service.ProfileService;
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
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepo profileRepo;
    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Override
    public Profile save(ProfileDto dto) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Recruiter not found for email: " + email));

        Profile profile = createProfile(dto);
        profile.setUser(user);

        Profile savedProfile = profileRepo.save(profile);
        profile.setId(savedProfile.getId());

        if (user != null) {
            user.setProfile(profile);
        }
        userRepo.save(user);

        return profile;
    }

    @Override
    public ProfileDto findById(Long id) {
        Profile profile = profileRepo.findById(id)
                .orElseThrow();
        return modelMapper.map(profile, ProfileDto.class);
    }

    @Override
    public void deleteById(Long id) {
        ProfileDto profileDto = findById(id);
        profileRepo.delete(modelMapper.map(profileDto, Profile.class));
    }

    @Override
    public List<ProfileDto> getAllProfiles() {
        return modelMapper.map(profileRepo.getAll(), new TypeToken<List<ProfileDto>>(){}.getType());
    }

    private Profile createProfile(ProfileDto dto) {
        return Profile.builder()
                .specialization(dto.getSpecialization())
                .experience(dto.getExperience())
                .expected_salary(dto.getExpected_salary())
                .english_level(dto.getEnglish_level())
                .position(dto.getPosition())
                .build();
    }
}
