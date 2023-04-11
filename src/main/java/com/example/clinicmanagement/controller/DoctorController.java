package com.example.clinicmanagement.controller;
import com.example.clinicmanagement.model.Doctor;
import com.example.clinicmanagement.dto.DoctorDTO;
import com.example.clinicmanagement.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("api/doctors")
public class DoctorController
{
   private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public List<DoctorDTO> getAllDoctors(){
        List<Doctor> doctors = doctorService.getDoctors();
        List<DoctorDTO> doctorDTOs = new ArrayList<>();
        int size = doctors.size();
        for(int i=0; i<size; i++){
            doctorDTOs.add(new DoctorDTO());
        }

        for(int i=0; i<size; i++){
            DoctorDTO doctorDTO = doctorDTOs.get(i);
            doctorDTO.toDTO(doctors.get(i));
        }
        return doctorDTOs;
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> registerNewDoctor(@Valid @RequestBody DoctorDTO doctorDTO){
//        Doctor doctor = new Doctor();
//        doctor.setDoctorDto(doctorDTO);
        return new ResponseEntity<>( doctorService.addDoctor(doctorDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{doctorId}")
    public void deleteDoctor(@PathVariable("doctorId") Long doctorId){
        doctorService.deleteDoctorById(doctorId);
    }

    @PutMapping(path = "/{doctorId}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable("doctorId") Long doctorId,
                                               @RequestBody @Valid DoctorDTO doctorDTO
                                               ){
            Doctor doctor = new Doctor();
            doctor.setDoctorDto(doctorDTO);
            doctorService.modifyDoctorInformation(doctorId,doctor);
            return  new ResponseEntity<>(doctorDTO,HttpStatus.OK);
    }
}
