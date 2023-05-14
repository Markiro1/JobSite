package service;

import com.example.helpMAMOCHKA.dto.profile.ProfileDto;
import com.example.helpMAMOCHKA.entity.Profile;
import com.example.helpMAMOCHKA.entity.User;
import com.example.helpMAMOCHKA.enums.English;
import com.example.helpMAMOCHKA.enums.Position;
import com.example.helpMAMOCHKA.enums.Specialization;
import com.example.helpMAMOCHKA.repository.ProfileRepo;
import com.example.helpMAMOCHKA.repository.UserRepo;
import com.example.helpMAMOCHKA.service.impl.ProfileServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProfileServiceImplTest {

    @Mock
    private ProfileRepo profileRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ProfileServiceImpl profileService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void save_shouldReturnSavedProfile() {
        ProfileDto dto = new ProfileDto();
        dto.setSpecialization(Specialization.ANDROID);
        dto.setExperience(2);
        dto.setExpected_salary("11111");
        dto.setEnglish_level(English.INTERMEDIATE);
        dto.setPosition(Position.BACKEND_DEVELOPER);

        Profile expectedProfile = new Profile();
        expectedProfile.setId(1L);
        expectedProfile.setSpecialization(Specialization.ANDROID);
        expectedProfile.setExperience(2);
        expectedProfile.setExpected_salary("11111");
        expectedProfile.setEnglish_level(English.INTERMEDIATE);
        expectedProfile.setPosition(Position.BACKEND_DEVELOPER);

        Authentication auth = mock(Authentication.class);
        when(auth.getName()).thenReturn("test@example.com");
        SecurityContextHolder.getContext().setAuthentication(auth);

        when(userRepo.findByEmail(any())).thenReturn(Optional.of(new User()));
        when(profileRepo.save(any())).thenReturn(expectedProfile);
        when(modelMapper.map(dto, Profile.class)).thenReturn(new Profile());

        Profile savedProfile = profileService.save(dto);

        assertNotNull(savedProfile);
        assertEquals(expectedProfile.getId(), savedProfile.getId());
        assertEquals(expectedProfile.getSpecialization(), savedProfile.getSpecialization());
        assertEquals(expectedProfile.getExperience(), savedProfile.getExperience());
        assertEquals(expectedProfile.getExpected_salary(), savedProfile.getExpected_salary());
        assertEquals(expectedProfile.getEnglish_level(), savedProfile.getEnglish_level());
        assertEquals(expectedProfile.getPosition(), savedProfile.getPosition());

        verify(userRepo, times(1)).findByEmail(any());
        verify(profileRepo, times(1)).save(any());
        verify(userRepo, times(1)).save(any());
    }

    @Test
    public void findById_shouldReturnProfileDto() {
        ProfileDto dto = new ProfileDto();
        dto.setSpecialization(Specialization.ANDROID);
        dto.setExperience(2);
        dto.setExpected_salary("11111");
        dto.setEnglish_level(English.INTERMEDIATE);
        dto.setPosition(Position.BACKEND_DEVELOPER);

        Profile expectedProfile = new Profile();
        expectedProfile.setId(1L);
        expectedProfile.setSpecialization(Specialization.ANDROID);
        expectedProfile.setExperience(2);
        expectedProfile.setExpected_salary("11111");
        expectedProfile.setEnglish_level(English.INTERMEDIATE);
        expectedProfile.setPosition(Position.BACKEND_DEVELOPER);

        when(profileRepo.findById(1L)).thenReturn(Optional.of(expectedProfile));
        when(modelMapper.map(expectedProfile, ProfileDto.class)).thenReturn(dto);

        ProfileDto actualDto = profileService.findById(1L);

        assertNotNull(actualDto);
        assertEquals(dto.getId(), actualDto.getId());
        assertEquals(dto.getSpecialization(), actualDto.getSpecialization());
        assertEquals(dto.getExperience(), actualDto.getExperience());
        assertEquals(dto.getExpected_salary(), actualDto.getExpected_salary());
        assertEquals(dto.getEnglish_level(), actualDto.getEnglish_level());
        assertEquals(dto.getPosition(), actualDto.getPosition());
    }

    @Test
    public void deleteById_shouldDeleteProfile() {
        ProfileDto profileDto = new ProfileDto();
        profileDto.setId(1L);
        profileDto.setSpecialization(Specialization.CSHARP);
        profileDto.setExperience(2);
        profileDto.setExpected_salary("10000");
        profileDto.setEnglish_level(English.ADVANCED_FLUENT);
        profileDto.setPosition(Position.CLOUD_ARCHITECT);

        Profile profile = new Profile();
        profile.setId(profileDto.getId());
        profile.setSpecialization(profileDto.getSpecialization());
        profile.setExperience(profileDto.getExperience());
        profile.setExpected_salary(profileDto.getExpected_salary());
        profile.setEnglish_level(profileDto.getEnglish_level());
        profile.setPosition(profileDto.getPosition());

        when(profileRepo.findById(1L)).thenReturn(Optional.of(profile));
        when(modelMapper.map(profile, ProfileDto.class)).thenReturn(profileDto);
        when(modelMapper.map(profileDto, Profile.class)).thenReturn(profile);

        profileService.deleteById(1L);

        verify(profileRepo, times(1)).delete(profile);
    }

    @Test
    public void getAllProfiles_shouldReturnAllProfiles() {
        List<Profile> profiles = new ArrayList<>();
        Profile profile1 = new Profile();
        profile1.setId(1L);
        profile1.setSpecialization(Specialization.CSHARP);
        profile1.setExperience(2);
        profile1.setExpected_salary("10000");
        profile1.setEnglish_level(English.ADVANCED_FLUENT);
        profile1.setPosition(Position.CLOUD_ARCHITECT);
        profiles.add(profile1);

        Profile profile2 = new Profile();
        profile2.setId(2L);
        profile2.setSpecialization(Specialization.JAVA);
        profile2.setExperience(5);
        profile2.setExpected_salary("15000");
        profile2.setEnglish_level(English.ADVANCED_FLUENT);
        profile2.setPosition(Position.BACKEND_DEVELOPER);
        profiles.add(profile2);

        List<ProfileDto> profileDtos = new ArrayList<>();
        ProfileDto profileDto1 = new ProfileDto();
        profileDto1.setId(1L);
        profileDto1.setSpecialization(Specialization.CSHARP);
        profileDto1.setExperience(2);
        profileDto1.setExpected_salary("10000");
        profileDto1.setEnglish_level(English.ADVANCED_FLUENT);
        profileDto1.setPosition(Position.CLOUD_ARCHITECT);
        profileDtos.add(profileDto1);

        ProfileDto profileDto2 = new ProfileDto();
        profileDto2.setId(2L);
        profileDto2.setSpecialization(Specialization.JAVA);
        profileDto2.setExperience(5);
        profileDto2.setExpected_salary("15000");
        profileDto2.setEnglish_level(English.ADVANCED_FLUENT);
        profileDto2.setPosition(Position.BACKEND_DEVELOPER);
        profileDtos.add(profileDto2);

        when(profileRepo.getAll()).thenReturn(profiles);
        when(modelMapper.map(profiles, new TypeToken<List<ProfileDto>>(){}.getType())).thenReturn(profileDtos);

        List<ProfileDto> result = profileService.getAllProfiles();

        Assertions.assertEquals(profileDtos.size(), result.size());
        Assertions.assertEquals(profileDtos.get(0).getId(), result.get(0).getId());
        Assertions.assertEquals(profileDtos.get(1).getId(), result.get(1).getId());
    }
}
