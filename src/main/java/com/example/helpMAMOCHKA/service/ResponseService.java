package com.example.helpMAMOCHKA.service;

import com.example.helpMAMOCHKA.dto.response.ResponseDto;
import com.example.helpMAMOCHKA.entity.Response;

import java.util.List;

public interface ResponseService {
    Response save(ResponseDto responseDto, Long vacationId);
    ResponseDto findById(Long id);
    void deleteById(Long id);
    List<ResponseDto> getAllResponses();
}
