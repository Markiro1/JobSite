package com.example.jobSite.service.impl;

import com.example.jobSite.dto.user.UserDto;
import com.example.jobSite.dto.user.UserWithoutPasswordDto;
import com.example.jobSite.entity.User;
import com.example.jobSite.repository.UserRepo;
import com.example.jobSite.service.UserService;
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
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final ModelMapper modelMapper;

    @Override
    public UserDto save(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);
        return modelMapper.map(userRepo.save(user), UserDto.class);
    }

    @Override
    public UserWithoutPasswordDto findById(Long id) {
        User user = userRepo.findById(id)
                .orElseThrow();
        return modelMapper.map(user, UserWithoutPasswordDto.class);
    }

    @Override
    public void deleteById(Long id) {
        UserWithoutPasswordDto userDto = findById(id);
        userRepo.delete(modelMapper.map(userDto, User.class));
    }

    @Override
    public List<UserWithoutPasswordDto> getAllUsers() {
        return modelMapper.map(userRepo.findAll(), new TypeToken<List<UserWithoutPasswordDto>>(){}.getType());
    }

    @Override
    public UserDto findByEmail(String email) {
        Optional<User> optionalUser = userRepo.findByEmail(email);
        return optionalUser.isEmpty() ? null : modelMapper.map(optionalUser.get(), UserDto.class);
    }
}
