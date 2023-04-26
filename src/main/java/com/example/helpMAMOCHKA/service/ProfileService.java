package com.example.helpMAMOCHKA.service;

import com.example.helpMAMOCHKA.dto.profile.ProfileDto;
import com.example.helpMAMOCHKA.entity.Profile;

import java.util.List;

public interface ProfileService {

    Profile save(ProfileDto dto);
    ProfileDto findById(Long id);
    void deleteById(Long id);
    List<ProfileDto> getAllProfiles();
}
