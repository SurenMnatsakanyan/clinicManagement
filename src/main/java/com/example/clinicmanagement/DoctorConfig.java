package com.example.clinicmanagement;
import com.example.clinicmanagement.model.Doctor;
import com.example.clinicmanagement.model.Specification;
import com.example.clinicmanagement.repository.DoctorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration
@ComponentScan
public class DoctorConfig {
    @Bean
    public CommandLineRunner commandLineRunner(DoctorRepository doctorRepository){
        return args -> {
            Doctor doctor1 = new Doctor();
            doctor1.setFirstName("Ara");
            doctor1.setLastName("Martirosyan");
            Set<Specification> specifications = new HashSet<>();
            specifications.add(Specification.ANESTHOLOGIST);
            specifications.add(Specification.DERMATOLGIST);
            specifications.add(Specification.CARDDIOLOGIST);
            doctor1.setSpecifications(specifications);
            doctor1.setYearsOfExperience(8);
            doctor1.setDate(LocalDate.of(1950, Month.APRIL,20));
            doctor1.setDescription("Hey giti sarer");
            Doctor doctor2 = new Doctor();
            doctor2.setFirstName("Ara");
            doctor2.setLastName("Martirosyan");
            Set<Specification> specifications2 = new HashSet<>();
            specifications2.add(Specification.FAMILYPHYSICAN);
            specifications2.add(Specification.GASTROENDRONOLOGIST);
            specifications2.add(Specification.CARDDIOLOGIST);
            doctor2.setSpecifications(specifications2);
            doctor2.setYearsOfExperience(8);
            doctor2.setDate(LocalDate.of(1980, Month.APRIL,20));
            doctor2.setDescription("Karmen jan ");
            doctorRepository.saveAll(List.of(doctor1,doctor2));
        };
    }
}
