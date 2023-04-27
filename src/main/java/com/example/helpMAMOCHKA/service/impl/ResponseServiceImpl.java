package com.example.helpMAMOCHKA.service.impl;

import com.example.helpMAMOCHKA.dto.response.ResponseDto;
import com.example.helpMAMOCHKA.dto.vacation.VacationDto;
import com.example.helpMAMOCHKA.entity.Recruiter;
import com.example.helpMAMOCHKA.entity.Response;
import com.example.helpMAMOCHKA.entity.User;
import com.example.helpMAMOCHKA.entity.Vacation;
import com.example.helpMAMOCHKA.exceptions.VacationNotFoundException;
import com.example.helpMAMOCHKA.repository.ResponseRepo;
import com.example.helpMAMOCHKA.repository.UserRepo;
import com.example.helpMAMOCHKA.repository.VacationRepo;
import com.example.helpMAMOCHKA.service.ResponseService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ResponseServiceImpl implements ResponseService {

    private final ResponseRepo responseRepo;
    private final UserRepo userRepo;
    private final VacationRepo vacationRepo;
    private final ModelMapper modelMapper;

    @Override
    public Response save(ResponseDto responseDto, Long vacationId) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userRepo.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Recruiter not found for email: " + email));

        Vacation vacation = vacationRepo.findById(vacationId)
                .orElseThrow(() -> new VacationNotFoundException("Vacation not exist with this id: " + vacationId));

        Response response = createResponseToVacation(responseDto);
        response.setUser(user);
        response.setVacation(vacation);

        Response savedResponse = responseRepo.save(response);
        response.setId(savedResponse.getId());

        if (user != null) {
            user.getResponses().add(response);
            userRepo.save(user);
        }
        return response;
    }

    @Override
    public ResponseDto findById(Long id) {
        Response response = responseRepo.findById(id)
                .orElseThrow();
        return modelMapper.map(response, ResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        ResponseDto responseDto = findById(id);
        responseRepo.delete(modelMapper.map(responseDto, Response.class));
    }

    @Override
    public List<ResponseDto> getAllResponses() {
        return modelMapper.map(responseRepo.findAll(), new TypeToken<List<ResponseDto>>(){}.getType());
    }

    private Response createResponseToVacation(ResponseDto dto) {
        return Response.builder()
                .message(dto.getMessage())
                .build();
    }
}
