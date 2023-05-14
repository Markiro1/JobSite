package service;

import com.example.helpMAMOCHKA.dto.user.UserDto;
import com.example.helpMAMOCHKA.dto.user.UserWithoutPasswordDto;
import com.example.helpMAMOCHKA.entity.User;
import com.example.helpMAMOCHKA.repository.UserRepo;
import com.example.helpMAMOCHKA.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepo userRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testSave() {
        // given
        UserDto userDto = new UserDto();
        userDto.setId(1L);
        userDto.setEmail("user@example.com");
        userDto.setPassword("password");

        User user = new User();
        user.setId(1L);
        user.setEmail("user@example.com");
        user.setPassword("password");

        when(modelMapper.map(userDto, User.class)).thenReturn(user);
        when(userRepo.save(user)).thenReturn(user);
        when(modelMapper.map(user, UserDto.class)).thenReturn(userDto);

        // when
        UserDto savedUserDto = userService.save(userDto);

        // then
        assertEquals(userDto.getId(), savedUserDto.getId());
        assertEquals(userDto.getEmail(), savedUserDto.getEmail());
        assertEquals(userDto.getPassword(), savedUserDto.getPassword());
    }

    @Test
    public void testFindById() {
        // given
        Long id = 1L;

        User user = new User();
        user.setId(id);
        user.setEmail("user@example.com");
        user.setPassword("password");

        UserWithoutPasswordDto userWithoutPasswordDto = new UserWithoutPasswordDto();
        userWithoutPasswordDto.setId(id);
        userWithoutPasswordDto.setEmail("user@example.com");

        when(userRepo.findById(id)).thenReturn(Optional.of(user));
        when(modelMapper.map(user, UserWithoutPasswordDto.class)).thenReturn(userWithoutPasswordDto);

        // when
        UserWithoutPasswordDto foundUserDto = userService.findById(id);

        // then
        assertEquals(id, foundUserDto.getId());
        assertEquals(user.getEmail(), foundUserDto.getEmail());
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindByIdNotFound() {
        // given
        Long id = 1L;

        when(userRepo.findById(id)).thenReturn(Optional.empty());

        // when
        userService.findById(id);

        // then throws NoSuchElementException
    }

    @Test
    public void testDeleteById() {
        // given
        Long id = 1L;

        UserWithoutPasswordDto userWithoutPasswordDto = new UserWithoutPasswordDto();
        userWithoutPasswordDto.setId(id);
        userWithoutPasswordDto.setEmail("user@example.com");

        User user = new User();
        user.setId(id);
        user.setEmail("user@example.com");
        user.setPassword("password");

        when(userRepo.findById(id)).thenReturn(Optional.of(user));
        doNothing().when(userRepo).delete(user);
        when(modelMapper.map(userWithoutPasswordDto, User.class)).thenReturn(user);
        when(modelMapper.map(user, UserWithoutPasswordDto.class)).thenReturn(userWithoutPasswordDto);

        // when
        userService.deleteById(id);

        // then
        verify(userRepo, times(1)).delete(user);
    }

    @Test(expected = NoSuchElementException.class)
    public void testDeleteByIdNotFound() {
        // given
        Long id = 1L;

        when(userRepo.findById(id)).thenReturn(Optional.empty());

        // when
        userService.deleteById(id);

        // then throws NoSuchElementException
    }

    @Test
    public void testGetAllUsers() {
        // given
        Long id = 1L;

        User user = new User();
        user.setId(id);
        user.setEmail("user@example.com");
        user.setPassword("password");

        List<User> users = Collections.singletonList(user);

        UserWithoutPasswordDto userWithoutPasswordDto = new UserWithoutPasswordDto();
        userWithoutPasswordDto.setId(id);
        userWithoutPasswordDto.setEmail("user@example.com");

        List<UserWithoutPasswordDto> expectedUsers = Collections.singletonList(userWithoutPasswordDto);

        when(userRepo.findAll()).thenReturn(users);
        when(modelMapper.map(user, UserWithoutPasswordDto.class)).thenReturn(userWithoutPasswordDto);
        when(modelMapper.map(userWithoutPasswordDto, User.class)).thenReturn(user);

        // when
        List<UserWithoutPasswordDto> foundUsers = userService.getAllUsers();

        // then
        verify(userRepo, times(1)).findAll();
    }
}
