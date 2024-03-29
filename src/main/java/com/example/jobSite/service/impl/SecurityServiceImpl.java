package com.example.jobSite.service.impl;

import com.example.jobSite.constant.ErrorMessage;
import com.example.jobSite.dto.recruiter.RecruiterDto;
import com.example.jobSite.dto.securityDto.SignInDto;
import com.example.jobSite.dto.securityDto.SignUpDto;
import com.example.jobSite.dto.securityDto.SuccessSignInDto;
import com.example.jobSite.dto.securityDto.SuccessSignUpDto;
import com.example.jobSite.dto.user.UserDto;
import com.example.jobSite.entity.Recruiter;
import com.example.jobSite.entity.User;
import com.example.jobSite.enums.Role;
import com.example.jobSite.exceptions.RecruiterAlreadyRegisteredException;
import com.example.jobSite.exceptions.UserAlreadyRegisteredException;
import com.example.jobSite.exceptions.WrongEmailException;
import com.example.jobSite.exceptions.WrongPasswordException;
import com.example.jobSite.repository.RecruiterRepo;
import com.example.jobSite.repository.UserRepo;
import com.example.jobSite.service.RecruiterService;
import com.example.jobSite.service.SecurityService;
import com.example.jobSite.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
@RequiredArgsConstructor
public class SecurityServiceImpl implements SecurityService {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;
    private final UserRepo userRepo;
    private final RecruiterRepo recruiterRepo;
    private final RecruiterService recruiterService;

    @Override
    public SuccessSignUpDto signUp(SignUpDto dto) {

        if (dto.getRole().equals(Role.ROLE_RECRUITER)) {
            return signUpAsRecruiter(dto);
        } else {
            return signUpAsUser(dto);
        }
    }

    @Override
    public SuccessSignInDto signIn(SignInDto dto) {
        if (dto.getRole().equals(Role.ROLE_RECRUITER)) {
            return singInAsRecruiter(dto);
        } else {
            return signInAsUser(dto);
        }
    }

    private SuccessSignUpDto signUpAsUser(SignUpDto dto) {
        User user = createNewUser(dto);
        try {
            User savedUser = userRepo.save(user);
            user.setId(savedUser.getId());
        } catch (DataIntegrityViolationException e) {
            throw new UserAlreadyRegisteredException(ErrorMessage.USER_ALREADY_REGISTERED_WITH_THIS_EMAIL);
        }
        return new SuccessSignUpDto(user.getId(), user.getNickname(), user.getEmail());
    }

    private SuccessSignUpDto signUpAsRecruiter(SignUpDto dto) {
        Recruiter recruiter = createNewRecruiter(dto);
        try {
            Recruiter savedRecruiter = recruiterRepo.save(recruiter);
            recruiter.setId(savedRecruiter.getId());
        } catch (DataIntegrityViolationException e) {
            throw new RecruiterAlreadyRegisteredException(ErrorMessage.RECRUITER_ALREADY_REGISTERED_WITH_THIS_EMAIL);
        }
        return new SuccessSignUpDto(recruiter.getId(), recruiter.getFullName(), recruiter.getEmail());
    }

    private User createNewUser(SignUpDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        User user = User.builder()
                .nickname(dto.getNickname())
                .firstName(dto.getFirstName())
                .dateOfCreated(LocalDateTime.now())
                .active(true)
                .build();

        user.setEmail(dto.getEmail());
        user.setPassword(encodedPassword);
        user.setRole(dto.getRole());

        return user;
    }

    private Recruiter createNewRecruiter(SignUpDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.getPassword());

        Recruiter recruiter = Recruiter.builder()
                .fullName(dto.getFirstName())
                .dateOfCreated(LocalDateTime.now())
                .active(true)
                .build();

        recruiter.setEmail(dto.getEmail());
        recruiter.setPassword(encodedPassword);
        recruiter.setRole(dto.getRole());

        return recruiter;
    }

    private SuccessSignInDto signInAsUser(SignInDto dto) {
        UserDto user = userService.findByEmail(dto.getEmail());
        if (user == null) {
            throw new WrongEmailException(ErrorMessage.USER_ALREADY_REGISTERED_WITH_THIS_EMAIL + dto.getEmail());
        }
        if (!isPasswordCorrectForUser(dto, user)) {
            throw new WrongPasswordException(ErrorMessage.BAD_PASSWORD);
        }
        return new SuccessSignInDto(user.getId(), user.getNickname());
    }

    private SuccessSignInDto singInAsRecruiter(SignInDto dto) {
        RecruiterDto recruiter = recruiterService.findByEmail(dto.getEmail());
        if (recruiter == null) {
            throw new WrongEmailException(ErrorMessage.USER_ALREADY_REGISTERED_WITH_THIS_EMAIL + dto.getEmail());
        }
        if (!isPasswordCorrectForRecruiter(dto, recruiter)) {
            throw new WrongPasswordException(ErrorMessage.BAD_PASSWORD);
        }
        return new SuccessSignInDto(recruiter.getId(), recruiter.getFullName());
    }

    private boolean isPasswordCorrectForUser(SignInDto signInDto, UserDto userDto) {
        if (userDto.getPassword() == null || userDto.getEmail() == null) {
            return false;
        }
        return passwordEncoder.matches(signInDto.getPassword(), userDto.getPassword());
    }

    private boolean isPasswordCorrectForRecruiter(SignInDto signInDto, RecruiterDto recruiterDto) {
        if (recruiterDto.getPassword() == null || recruiterDto.getEmail() == null) {
            return false;
        }
        return passwordEncoder.matches(signInDto.getPassword(), recruiterDto.getPassword());
    }
}