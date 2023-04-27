package com.example.helpMAMOCHKA.controller;

import com.example.helpMAMOCHKA.dto.response.ResponseDto;
import com.example.helpMAMOCHKA.entity.Response;
import com.example.helpMAMOCHKA.service.ResponseService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responses")
@AllArgsConstructor
@Validated
public class ResponseController {

    private final ResponseService responseService;

    @PostMapping("/addResponse/{vacationId}")
    public ResponseEntity<Response> createResponseForVacationFromUser(@Valid @RequestBody ResponseDto responseDto,
                                                                      @PathVariable Long vacationId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(responseService.save(responseDto, vacationId));
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<ResponseDto> findById(@PathVariable(value = "id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(responseService.findById(id));
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable(value = "id") Long userId) {
        responseService.deleteById(userId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<ResponseDto>> getAllUsers() {
        return ResponseEntity.status(HttpStatus.OK).body(responseService.getAllResponses());
    }
}
