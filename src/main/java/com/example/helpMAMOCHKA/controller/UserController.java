package com.example.helpMAMOCHKA.controller;

import com.example.helpMAMOCHKA.dto.user.UserWithoutPasswordDto;
import com.example.helpMAMOCHKA.service.UserService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
@Validated

public class UserController {

    private final UserService userService;

    @GetMapping("/findById/{id}")
    public ResponseEntity<UserWithoutPasswordDto> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") Long userId) {
        userService.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserWithoutPasswordDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAllUsers());
    }
}
