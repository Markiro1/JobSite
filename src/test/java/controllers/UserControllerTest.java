package controllers;

import com.example.jobSite.controller.UserController;
import com.example.jobSite.dto.user.UserWithoutPasswordDto;
import com.example.jobSite.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

    private MockMvc mockMvc;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void findById_WhenUserExists_ReturnsUser() throws Exception {
        // Arrange
        UserWithoutPasswordDto expectedUser = new UserWithoutPasswordDto();
        expectedUser.setId(1L);
        expectedUser.setNickname("johndoe");
        expectedUser.setEmail("johndoe@example.com");

        Mockito.when(userService.findById(1L)).thenReturn(expectedUser);

        // Act and Assert
        mockMvc.perform(MockMvcRequestBuilders.get("/findById/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nickname").value("johndoe"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value("johndoe@example.com"));
    }
}
