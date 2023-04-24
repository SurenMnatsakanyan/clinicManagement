package com.example.clinicmanagement.Doctor;

import com.example.clinicmanagement.model.Doctor;
import com.example.clinicmanagement.model.Specification;
import com.example.clinicmanagement.repository.DoctorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class DoctorRepositoryTest {
    @Autowired
    private DoctorRepository doctorRepository;

    @AfterEach
    void tearDown() {
        doctorRepository.deleteAll();
    }

    @Test
    public void isDoctorByIDExists() {
        Specification specification1 = new Specification();
        specification1.setName("Endocrinology");
        Specification specification2 = new Specification();
        specification2.setName("Imunologist");
        Doctor doctor = new Doctor();
        doctor.setDate(LocalDate.of(2001, 2, 12));
        doctor.setDescription("Working hard on dreams!");
        doctor.setFirstName("Meruj");
        doctor.setLastName("Hadidyan");
        doctor.setSpecifications(Set.of(specification1, specification2));
        doctorRepository.save(doctor);
        Doctor optionalDoctor = doctorRepository.findById(1L).orElseThrow(() -> new RuntimeException("Doctor doesn't exist"));
        assertEquals(doctor, optionalDoctor);
    }
}
