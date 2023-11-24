package com.example.jobSite.service;

import com.example.jobSite.dto.response.ResponseDto;
import com.example.jobSite.entity.Response;

import java.util.List;

public interface ResponseService {
    Response save(ResponseDto responseDto, Long vacationId);
    ResponseDto findById(Long id);
    void deleteById(Long id);
    List<ResponseDto> getAllResponses();
}
