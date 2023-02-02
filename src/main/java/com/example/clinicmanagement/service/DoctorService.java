package com.example.clinicmanagement.service;
import com.example.clinicmanagement.model.Doctor;
import com.example.clinicmanagement.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository){
        this.doctorRepository = doctorRepository;
    }

    public List<Doctor> getDoctors() {
        return doctorRepository.findAll();
    }

    public void addDoctor(Doctor doctor) {
       doctorRepository.save(doctor);
    }

    public void deleteDoctorById(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Transactional
    public void modifyDoctorInformation(Long doctorId, Doctor doctor) {
        Doctor doctorAssumed = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException("Doctor with " + doctorId + " doesn't exist"));

    }
}
