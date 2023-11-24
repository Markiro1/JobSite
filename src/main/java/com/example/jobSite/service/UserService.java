package com.example.jobSite.service;

import com.example.jobSite.dto.user.UserDto;
import com.example.jobSite.dto.user.UserWithoutPasswordDto;

import java.util.List;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto findByEmail(String email);

    UserWithoutPasswordDto findById(Long id);

    void deleteById(Long id);

    List<UserWithoutPasswordDto> getAllUsers();
}
