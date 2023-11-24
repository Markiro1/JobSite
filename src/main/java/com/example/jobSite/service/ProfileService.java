package com.example.jobSite.service;

import com.example.jobSite.dto.profile.ProfileDto;
import com.example.jobSite.entity.Profile;

import java.util.List;

public interface ProfileService {

    Profile save(ProfileDto dto);
    ProfileDto findById(Long id);
    void deleteById(Long id);
    List<ProfileDto> getAllProfiles();
}
