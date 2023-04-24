package com.example.clinicmanagement.service;

import com.example.clinicmanagement.Excpetion.NotFoundException;
import com.example.clinicmanagement.dto.DoctorDTO;
import com.example.clinicmanagement.model.Doctor;
import com.example.clinicmanagement.model.Specification;
import com.example.clinicmanagement.repository.DoctorRepository;
import com.example.clinicmanagement.repository.SpecificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final SpecificationRepository specificationRepository;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, SpecificationRepository specificationRepository) {
        this.doctorRepository = doctorRepository;
        this.specificationRepository = specificationRepository;
    }

    @Transactional(readOnly = true)
    public List<DoctorDTO> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDTO> doctorDTOs = new ArrayList<>();
        int size = doctors.size();
        for (int i = 0; i < size; i++) {
            doctorDTOs.add(new DoctorDTO());
        }

        for (int i = 0; i < size; i++) {
            DoctorDTO doctorDTO = doctorDTOs.get(i);
            doctorDTO.toDTO(doctors.get(i));
        }
        return doctorDTOs;
    }

    @Transactional
    public DoctorDTO addDoctor(DoctorDTO doctorDTO) throws NotFoundException {
        List<Long> specificationId = doctorDTO.getSpecificationIds();
        Set<Specification> specifications = new HashSet<>();
        for (Long id : specificationId) {
            Specification specification = specificationRepository.findSpecificationById(id).orElseThrow(() -> new NotFoundException("Specification by " + specificationId + " not found"));
            specifications.add(specification);
        }

        Doctor doctor = new Doctor();
        doctor.setDoctorDto(doctorDTO);
        doctor.setSpecifications(specifications);
        Doctor savedDoctor = doctorRepository.save(doctor);
        doctorDTO.toDTO(savedDoctor);
        return doctorDTO;
    }


    public void deleteDoctorById(Long doctorId) {
        doctorRepository.deleteById(doctorId);
    }

    @Transactional
    public void modifyDoctorInformation(Long doctorId, DoctorDTO doctorDTO) {
        Doctor doctor = new Doctor();
        doctor.setDoctorDto(doctorDTO);
        Doctor doctorAssumed = doctorRepository.findById(doctorId).orElseThrow(() -> new RuntimeException(String.format("Doctor with id: %s doesn't exist", doctorId)));
        doctor.setDoctor(doctorAssumed);
    }
}
