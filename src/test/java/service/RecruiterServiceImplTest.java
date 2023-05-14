package service;

import com.example.helpMAMOCHKA.dto.recruiter.RecruiterDto;
import com.example.helpMAMOCHKA.dto.recruiter.RecruiterWithoutPasswordDto;
import com.example.helpMAMOCHKA.entity.Recruiter;
import com.example.helpMAMOCHKA.repository.RecruiterRepo;
import com.example.helpMAMOCHKA.service.impl.RecruiterServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RecruiterServiceImplTest {

    @Mock
    private RecruiterRepo recruiterRepo;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private RecruiterServiceImpl recruiterService;

    @Test
    public void testSave() {
        RecruiterDto inputDto = new RecruiterDto();
        Recruiter inputRecruiter = new Recruiter();
        Recruiter savedRecruiter = new Recruiter();
        RecruiterDto expectedDto = new RecruiterDto();

        Mockito.when(modelMapper.map(inputDto, Recruiter.class)).thenReturn(inputRecruiter);
        Mockito.when(recruiterRepo.save(inputRecruiter)).thenReturn(savedRecruiter);
        Mockito.when(modelMapper.map(savedRecruiter, RecruiterDto.class)).thenReturn(expectedDto);

        RecruiterDto actualDto = recruiterService.save(inputDto);

        assertEquals(expectedDto, actualDto);
        Mockito.verify(recruiterRepo, Mockito.times(1)).save(inputRecruiter);
        Mockito.verify(modelMapper, Mockito.times(1)).map(inputDto, Recruiter.class);
        Mockito.verify(modelMapper, Mockito.times(1)).map(savedRecruiter, RecruiterDto.class);
    }

    @Test
    public void testFindByEmail() {
        String email = "test@example.com";
        Optional<Recruiter> optionalRecruiter = Optional.of(new Recruiter());
        RecruiterDto expectedDto = new RecruiterDto();

        Mockito.when(recruiterRepo.findByEmail(email)).thenReturn(optionalRecruiter);
        Mockito.when(modelMapper.map(optionalRecruiter.get(), RecruiterDto.class)).thenReturn(expectedDto);

        RecruiterDto actualDto = recruiterService.findByEmail(email);

        assertEquals(expectedDto, actualDto);
        Mockito.verify(recruiterRepo, Mockito.times(1)).findByEmail(email);
        Mockito.verify(modelMapper, Mockito.times(1)).map(optionalRecruiter.get(), RecruiterDto.class);
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Recruiter recruiter = new Recruiter();
        RecruiterWithoutPasswordDto expectedDto = new RecruiterWithoutPasswordDto();

        Mockito.when(recruiterRepo.findById(id)).thenReturn(Optional.of(recruiter));
        Mockito.when(modelMapper.map(recruiter, RecruiterWithoutPasswordDto.class)).thenReturn(expectedDto);

        RecruiterWithoutPasswordDto actualDto = recruiterService.findById(id);

        assertEquals(expectedDto, actualDto);
        Mockito.verify(recruiterRepo, Mockito.times(1)).findById(id);
        Mockito.verify(modelMapper, Mockito.times(1)).map(recruiter, RecruiterWithoutPasswordDto.class);
    }

    @Test(expected = NoSuchElementException.class)
    public void testFindByIdNotFound() {
        Long id = 1L;

        Mockito.when(recruiterRepo.findById(id)).thenReturn(Optional.empty());

        recruiterService.findById(id);
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        RecruiterWithoutPasswordDto dto = new RecruiterWithoutPasswordDto();
        dto.setId(id);
        dto.setFullName("HAHAH");
        dto.setCompany("HAC");

        Recruiter recruiter = new Recruiter();
        recruiter.setId(dto.getId());
        recruiter.setFullName(dto.getFullName());
        recruiter.setCompany(dto.getCompany());

        Mockito.when(recruiterRepo.findById(id)).thenReturn(Optional.of(recruiter));
        Mockito.when(modelMapper.map(recruiter, RecruiterWithoutPasswordDto.class)).thenReturn(dto);
        Mockito.when(modelMapper.map(dto, Recruiter.class)).thenReturn(recruiter);
        Mockito.when(modelMapper.map(recruiter, RecruiterWithoutPasswordDto.class)).thenReturn(dto);

        recruiterService.deleteById(id);

        Mockito.verify(recruiterRepo, Mockito.times(1)).findById(id);
        Mockito.verify(recruiterRepo, Mockito.times(1)).delete(recruiter);
        Mockito.verify(modelMapper, Mockito.times(1)).map(dto, Recruiter.class);
    }
}

