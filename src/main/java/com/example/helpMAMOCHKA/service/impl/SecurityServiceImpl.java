package com.example.helpMAMOCHKA.service.impl;

import com.example.helpMAMOCHKA.constant.ErrorMessage;
import com.example.helpMAMOCHKA.dto.securityDto.SignInDto;
import com.example.helpMAMOCHKA.dto.securityDto.SignUpDto;
import com.example.helpMAMOCHKA.dto.securityDto.SuccessSignInDto;
import com.example.helpMAMOCHKA.dto.securityDto.SuccessSignUpDto;
import com.example.helpMAMOCHKA.dto.user.UserDto;
import com.example.helpMAMOCHKA.entity.User;
import com.example.helpMAMOCHKA.enums.Role;
import com.example.helpMAMOCHKA.exceptions.UserAlreadyRegisteredException;
import com.example.helpMAMOCHKA.exceptions.WrongEmailException;
import com.example.helpMAMOCHKA.exceptions.WrongPasswordException;
import com.example.helpMAMOCHKA.repository.UserRepo;
import com.example.helpMAMOCHKA.service.SecurityService;
import com.example.helpMAMOCHKA.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserRepo userRepo;

    @Autowired
    public SecurityServiceImpl(PasswordEncoder passwordEncoder, UserService userService, UserRepo userRepo) {
        this.userService = userService;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public SuccessSignUpDto signUp(SignUpDto dto) {
        User user = createNewUser(dto);
        try {
            User savedUser = userRepo.save(user);
            user.setId(savedUser.getId());

        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyRegisteredException(ErrorMessage.USER_ALREADY_REGISTERED_WITH_THIS_EMAIL);
        }
        return new SuccessSignUpDto(user.getId(), user.getNickname(), user.getEmail());
    }

    private User createNewUser(SignUpDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());
        return User.builder()
                .nickname(dto.getNickname())
                .firstName(dto.getFirstName())
                .email(dto.getEmail())
                .password(encodedPassword)
                .dateOfCreated(LocalDateTime.now())
                .role(Role.ROLE_USER)
                .active(true)
                .build();
    }

    @Override
    public SuccessSignInDto signIn(SignInDto dto) {
        UserDto user = userService.findByEmail(dto.getEmail());
        if (user == null) {
            throw new WrongEmailException(ErrorMessage.USER_ALREADY_REGISTERED_WITH_THIS_EMAIL + dto.getEmail());
        }
        if (!isPasswordCorrect(dto, user)) {
            throw new WrongPasswordException(ErrorMessage.BAD_PASSWORD);
        }
        return new SuccessSignInDto(user.getId(), user.getNickname());
    }

    private boolean isPasswordCorrect(SignInDto signInDto, UserDto userDto) {
        if (userDto.getPassword() == null || userDto.getEmail() == null) {
            return false;
        }
        System.out.println(signInDto.getPassword() +  " ||||||| " + userDto.getPassword());
        return passwordEncoder.matches(signInDto.getPassword(), userDto.getPassword());
    }
}
