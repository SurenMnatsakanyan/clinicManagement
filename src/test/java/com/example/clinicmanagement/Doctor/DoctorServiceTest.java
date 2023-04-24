package com.example.clinicmanagement.Doctor;

import com.example.clinicmanagement.dto.DoctorDTO;
import com.example.clinicmanagement.model.Doctor;
import com.example.clinicmanagement.model.Specification;
import com.example.clinicmanagement.repository.DoctorRepository;
import com.example.clinicmanagement.repository.SpecificationRepository;
import com.example.clinicmanagement.service.DoctorService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class DoctorServiceTest {

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private SpecificationRepository specificationRepository;

    private AutoCloseable autoCloseable;


    private DoctorService doctorService;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        doctorService = new DoctorService(doctorRepository, specificationRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void canGetDoctors() {
        //when
        doctorService.getDoctors();
        //then
        verify(doctorRepository).findAll();
    }

    @Test
    void addDoctor() {
        Specification specification1 = new Specification();
        specification1.setName("Endocrinolog");
        specification1.setId(1L);
        Specification specification2 = new Specification();
        specification2.setId(2L);
        specification2.setName("Family physician");
        List<Specification> specificationList = List.of(specification1, specification2);
        DoctorDTO doctorDTO = new DoctorDTO();
        doctorDTO.setDate(LocalDate.of(2001, 2, 12));
        doctorDTO.setDescription("Working hard on dreams!");
        doctorDTO.setSpecificationIds(List.of(1L, 2L));
        doctorDTO.setFirstName("Meruj");
        doctorDTO.setLastName("Hadidyan");
        List<Long> specificationIds = doctorDTO.getSpecificationIds();
        Doctor doctorExpected = new Doctor();
        doctorExpected.setDoctorDto(doctorDTO);
        doctorExpected.setSpecifications(Set.of(specification1, specification2));
        Doctor outcomeFromDatabase = new Doctor();
        outcomeFromDatabase.setDoctor(doctorExpected);
        outcomeFromDatabase.setId(1L);
        outcomeFromDatabase.setSpecifications(Set.of(specification1, specification2));
        given(doctorRepository.save(doctorExpected)).willReturn(outcomeFromDatabase);
        int i = 0;
        while (i != specificationIds.size()) {
            given(specificationRepository.findSpecificationById(specificationIds.get(i)))
                    .willReturn(Optional.ofNullable(specificationList.get(i)));
            i++;
        }
        DoctorDTO result = doctorService.addDoctor(doctorDTO);
        ArgumentCaptor<Doctor> doctorArgumentCaptor = ArgumentCaptor.forClass(Doctor.class);
        verify(doctorRepository).save(doctorArgumentCaptor.capture());
        assertEquals(doctorExpected, doctorArgumentCaptor.getValue());
        DoctorDTO resultExpected = new DoctorDTO();
        resultExpected.toDTO(outcomeFromDatabase);
        resultExpected.setSpecificationIds(List.of(1L, 2L));
        assertEquals(resultExpected, result);
    }

    @Test
    void deleteDoctorById() {
        Long id = 2L;
        doctorService.deleteDoctorById(id);
        verify(doctorRepository, times(1)).deleteById(id);
    }
}