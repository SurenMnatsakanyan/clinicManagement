package com.example.clinicmanagement.Doctor;

import com.example.clinicmanagement.dto.DoctorDTO;
import com.example.clinicmanagement.dto.SpecificationDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static net.bytebuddy.matcher.ElementMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest
class DoctorControllerTest {

//    @MockBean
//    private DoctorService doctorService;

    @Autowired
    private MockMvc mvc;


    @Test
    public void getAllDoctors() throws Exception {
//
//        given(jwtService.generateToken(AppUser.builder()
//                        .id(1)
//                        .firstName("Suren")
//                        .lastName("Mnatsakanyan")
//                        .role(Role.ADMIN)
//                        .email("Smnatsakanyan@gmail.com")
//                        .password("1234567")
//                        .build())).willReturn("123456789");
//     ResponseEntity<AuthenticationResponse> responseResponseEntity= authenticationController.register(RegisterRequest.builder()
//             .firstName("Suren")
//             .lastName("Mnatsakanyan")
//             .email("smnatsakanyan@gmail.com")
//             .password("123456")
//             .build()
//     );


        DoctorDTO doctorDTO1 = new DoctorDTO();
        doctorDTO1.setId(1L);
        doctorDTO1.setFirstName("Suren");
        doctorDTO1.setLastName("Mnatsakanyan");
        doctorDTO1.setDescription("Working hard");
        doctorDTO1.setDate(LocalDate.of(2001, 12, 2));
        doctorDTO1.setSpecificationDTOs(Set.of(SpecificationDTO
                .builder()
                .id(1L)
                .name("Endocrinolog")
                .build(), SpecificationDTO.builder()
                .id(2L)
                .name("Family Physican")
                .build()
        ));
        List<DoctorDTO> doctorDTOS = List.of(doctorDTO1);
//        given(doctorService.getDoctors()).willReturn(doctorDTOS);
        ResultActions resultActions = mvc.perform(get("/api/doctors").contentType(MediaType.APPLICATION_JSON).with(SecurityMockMvcRequestPostProcessors.jwt()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect((ResultMatcher) jsonPath("$[0].id", is(doctorDTO1.getId().intValue())))
                .andExpect((ResultMatcher) jsonPath("$[0].firstName", is(doctorDTO1.getFirstName())))
                .andExpect((ResultMatcher) jsonPath("$[0].lastName", is(doctorDTO1.getLastName())))
                .andExpect((ResultMatcher) jsonPath("$[0].description", is(doctorDTO1.getDescription())))
                .andExpect((ResultMatcher) jsonPath("$[0].date", is(doctorDTO1.getDate().toString())))
                .andExpect((ResultMatcher) jsonPath("$[0].specificationDTOs[0].id", is(doctorDTO1.getSpecificationDTOs().iterator().next().getId().intValue())))
                .andExpect((ResultMatcher) jsonPath("$[0].specificationDTOs[0].name", is(doctorDTO1.getSpecificationDTOs().iterator().next().getName())))
                .andExpect((ResultMatcher) jsonPath("$[0].specificationDTOs[1].id", is(doctorDTO1.getSpecificationDTOs().stream().skip(1).findFirst().get().getId().intValue())))
                .andExpect((ResultMatcher) jsonPath("$[0].specificationDTOs[1].name", is(doctorDTO1.getSpecificationDTOs().stream().skip(1).findFirst().get().getName())));
    }

//    @Test
//    void registerNewDoctor() {
//
//    }
//
//    @Test
//    void deleteDoctor() {
//    }
//
//    @Test
//    void updateDoctor() {
//    }

}