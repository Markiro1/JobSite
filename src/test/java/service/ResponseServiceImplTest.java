package service;

import com.example.helpMAMOCHKA.dto.response.ResponseDto;
import com.example.helpMAMOCHKA.entity.Response;
import com.example.helpMAMOCHKA.entity.User;
import com.example.helpMAMOCHKA.entity.Vacation;
import com.example.helpMAMOCHKA.repository.ResponseRepo;
import com.example.helpMAMOCHKA.repository.UserRepo;
import com.example.helpMAMOCHKA.repository.VacationRepo;
import com.example.helpMAMOCHKA.service.impl.ResponseServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ResponseServiceImplTest {

    @Mock
    private ResponseRepo responseRepo;

    @Mock
    private UserRepo userRepo;

    @Mock
    private VacationRepo vacationRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private ResponseServiceImpl responseService;

    @Test
    public void save_shouldCreateResponseToVacation() {
        Long vacationId = 1L;
        String message = "Test message";

        // create mock vacation and user
        Vacation vacation = new Vacation();
        vacation.setId(vacationId);
        User user = new User();
        user.setId(1L);

        // mock vacation and user repositories
        when(vacationRepo.findById(vacationId)).thenReturn(Optional.of(vacation));
        when(userRepo.findByEmail(any())).thenReturn(Optional.of(user));

        // create mock authentication object
        Authentication auth = mock(Authentication.class);
        SecurityContextHolder.getContext().setAuthentication(auth);
        when(auth.getName()).thenReturn("test@example.com");

        // create response dto
        ResponseDto responseDto = new ResponseDto();
        responseDto.setMessage(message);

        Response response = new Response();
        response.setId(1L);

        when(responseRepo.save(any(Response.class))).thenReturn(response);

        // call save method
        Response savedResponse = responseService.save(responseDto, vacationId);

        // assert response object
        assertNotNull(savedResponse);
        assertEquals(message, savedResponse.getMessage());
        assertNotNull(savedResponse.getUser());
        assertEquals(user.getId(), savedResponse.getUser().getId());
        assertNotNull(savedResponse.getVacation());
        assertEquals(vacation.getId(), savedResponse.getVacation().getId());
        assertNotNull(savedResponse.getId());

        // verify repositories were called
        verify(responseRepo, times(1)).save(any());
        verify(userRepo, times(1)).findByEmail(any());
        verify(vacationRepo, times(1)).findById(any());
    }

    @Test
    public void findById_shouldReturnResponseDto() {
        Long responseId = 1L;

        Response response = new Response();
        response.setId(responseId);

        ResponseDto responseDto = new ResponseDto();
        responseDto.setId(responseId);

        when(responseRepo.findById(responseId)).thenReturn(Optional.of(response));
        when(modelMapper.map(response, ResponseDto.class)).thenReturn(responseDto);

        ResponseDto result = responseService.findById(responseId);

        assertEquals(responseDto, result);
        assertEquals(responseId, result.getId());

        verify(responseRepo, times(1)).findById(responseId);
    }

    @Test(expected = NoSuchElementException.class)
    public void findById_shouldThrowNoSuchElementException() {
        Long responseId = 1L;

        when(responseRepo.findById(responseId)).thenReturn(Optional.empty());

        responseService.findById(responseId);
    }

    @Test
    public void deleteById_shouldDeleteResponse() {
        Long responseId = 1L;

        ResponseDto responseDto = new ResponseDto();
        responseDto.setId(responseId);
        responseDto.setMessage("test");

        Response response = new Response();
        response.setId(responseId);
        response.setMessage(responseDto.getMessage());

        when(responseRepo.findById(responseId)).thenReturn(Optional.of(response));
        when(modelMapper.map(responseDto, Response.class)).thenReturn(response);
        when(modelMapper.map(response, ResponseDto.class)).thenReturn(responseDto);

        responseService.deleteById(responseId);

        verify(responseRepo, times(1)).delete(response);
    }

    @Test
    public void getAllResponses_shouldReturnListOfResponseDto() {
        Long responseId = 1L;
        ResponseDto responseDto = new ResponseDto();
        responseDto.setId(responseId);
        responseDto.setMessage("Test message");
        Response response = new Response();
        response.setId(responseId);
        response.setMessage(responseDto.getMessage());
        List<Response> responses = Collections.singletonList(response);

        // mock the response repository
        when(responseRepo.findAll()).thenReturn(responses);
        when(modelMapper.map(response, ResponseDto.class)).thenReturn(responseDto);
        when(modelMapper.map(responseDto, Response.class)).thenReturn(response);

        // call the service method
        List<ResponseDto> foundResponseDtos = responseService.getAllResponses();

        // verify the results
        verify(responseRepo, times(1)).findAll();
    }
}
