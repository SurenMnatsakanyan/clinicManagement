package com.example.clinicmanagement;

import com.example.clinicmanagement.model.Doctor;
import com.example.clinicmanagement.model.Specification;
import com.example.clinicmanagement.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.Month;
@Service
public class InitialData {
    @Autowired
    private DoctorRepository doctorRepository;


    @EventListener(
            ContextRefreshedEvent.class
    )
    @Transactional
    public void init() {
        Specification specification1 = new Specification("Endocrinolog");
        Specification specification2 = new Specification("Gastroendcorinolog");

        Doctor doctor1 = new Doctor();
        doctor1.getSpecifications().add(specification1);
        doctor1.getSpecifications().add(specification2);
        System.out.println("Specification one is " + doctor1.getSpecifications().size());
        doctor1.setFirstName("Armen");
        doctor1.setLastName("Gevorgyan");
        doctor1.setDescription("Worked in  many hospitals");
        doctor1.setYearsOfExperience(12);
        doctor1.setDate(LocalDate.of(1980, Month.DECEMBER, 12));


        Doctor doctor2 = new Doctor();
        doctor2.getSpecifications().add(specification1);
        doctor2.setFirstName("Gevorg");
        doctor2.setLastName("Gevorgyan");
        doctor2.setDescription("Worked in  many hospitals");
        doctor2.setYearsOfExperience(12);
        doctor2.setDate(LocalDate.of(1989, Month.APRIL, 13));
        doctorRepository.save(doctor1);
        doctorRepository.save(doctor2);
    }
}
