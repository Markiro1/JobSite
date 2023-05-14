package controllers;

import com.example.helpMAMOCHKA.controller.ProfileController;
import com.example.helpMAMOCHKA.dto.profile.ProfileDto;
import com.example.helpMAMOCHKA.entity.Profile;
import com.example.helpMAMOCHKA.enums.Position;
import com.example.helpMAMOCHKA.enums.Specialization;
import com.example.helpMAMOCHKA.service.ProfileService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RequiredArgsConstructor
public class ProfileControllerTest {


    private MockMvc mockMvc;

    @Mock
    private ProfileService profileService;

    @InjectMocks
    private ProfileController profileController;

    @BeforeEach
    void setup() {
        this.mockMvc = MockMvcBuilders
                .standaloneSetup(profileController)
                .build();
    }

    @Test
    public void testCreateProfile() throws Exception {
        ProfileDto dto = new ProfileDto();
        dto.setSpecialization(Specialization.ANDROID);
        dto.setPosition(Position.CLOUD_ARCHITECT);
        dto.setExpected_salary("1000");

        Profile profile = new Profile();
        profile.setSpecialization(Specialization.ANDROID);
        dto.setPosition(Position.CLOUD_ARCHITECT);
        dto.setExpected_salary("1000");

        when(profileService.save(dto)).thenReturn(profile);

        mockMvc.perform(post("/createProfile")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testFindById() throws Exception {
        Long id = 1L;
        ProfileDto dto = new ProfileDto();
        dto.setId(id);
        dto.setSpecialization(Specialization.ANDROID);
        dto.setPosition(Position.CLOUD_ARCHITECT);
        dto.setExpected_salary("1000");

        Profile profile = new Profile();
        profile.setId(id);
        profile.setSpecialization(Specialization.ANDROID);
        dto.setPosition(Position.CLOUD_ARCHITECT);
        dto.setExpected_salary("1000");

        when(profileService.findById(id)).thenReturn(dto);

        mockMvc.perform(get("/findById/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteById() throws Exception {
        Long id = 1L;

        mockMvc.perform(delete("/deleteById/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(profileService, times(1)).deleteById(id);
    }
}
