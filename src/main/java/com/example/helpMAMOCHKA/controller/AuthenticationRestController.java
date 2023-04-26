package com.example.helpMAMOCHKA.controller;

import com.example.helpMAMOCHKA.dto.auth.AuthenticationRequestDto;
import com.example.helpMAMOCHKA.entity.Recruiter;
import com.example.helpMAMOCHKA.entity.User;
import com.example.helpMAMOCHKA.enums.Role;
import com.example.helpMAMOCHKA.jwt.JwtTokenProvider;
import com.example.helpMAMOCHKA.repository.RecruiterRepo;
import com.example.helpMAMOCHKA.repository.UserRepo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationRestController {

    private final AuthenticationManager authenticationManager;
    private final UserRepo userRepository;
    private final RecruiterRepo recruiterRepo;
    private final JwtTokenProvider jwtTokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(),
                    requestDto.getPassword()));

            Optional<User> user = userRepository.findByEmail(requestDto.getEmail());
            Optional<Recruiter> recruiter = recruiterRepo.findByEmail(requestDto.getEmail());

            if (user.isPresent()) {
                return authenticateAsUser(requestDto, user.get());
            }
            if (recruiter.isPresent()) {
                return authenticateAsRecruiter(requestDto, recruiter.get());
            }

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found " + requestDto.getEmail());
        } catch (AuthenticationException e) {
            return new ResponseEntity<>("Invalid email/password combination", HttpStatus.FORBIDDEN);
        }
    }

    private ResponseEntity<?> authenticateAsUser(AuthenticationRequestDto requestDto, User user) {
        String token = jwtTokenProvider.generateToken(requestDto.getEmail(), user.getRole().name());
        Map<Object, Object> response = new HashMap<>();
        if (user.getRole().equals(Role.ROLE_USER) || user.getRole().equals(Role.ROLE_ADMIN)) {
            response.put("username", user.getNickname());
            response.put("fistName", user.getFirstName());
            response.put("id", user.getId());
            response.put("email", user.getEmail());
            response.put("role", user.getRole());
            response.put("token", token);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    private ResponseEntity<?> authenticateAsRecruiter(AuthenticationRequestDto requestDto, Recruiter recruiter) {
        String token = jwtTokenProvider.generateToken(requestDto.getEmail(), recruiter.getRole().name());
        Map<Object, Object> response = new HashMap<>();
        response.put("username", recruiter.getFullName());
        response.put("id", recruiter.getId());
        response.put("email", recruiter.getEmail());
        response.put("role", recruiter.getRole());
        response.put("token", token);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
