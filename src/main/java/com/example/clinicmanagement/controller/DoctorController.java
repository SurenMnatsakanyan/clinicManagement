package com.example.clinicmanagement.controller;

import com.example.clinicmanagement.dto.DoctorDTO;
import com.example.clinicmanagement.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        return new ResponseEntity<>(doctorService.getDoctors(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DoctorDTO> registerNewDoctor(@Valid @RequestBody DoctorDTO doctorDTO) {
        return new ResponseEntity<>(doctorService.addDoctor(doctorDTO), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{doctorId}")
    public void deleteDoctor(@PathVariable("doctorId") Long doctorId) {
        doctorService.deleteDoctorById(doctorId);
    }

    @PutMapping(path = "/{doctorId}")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable("doctorId") Long doctorId,
                                                  @RequestBody @Valid DoctorDTO doctorDTO
    ) {
        doctorService.modifyDoctorInformation(doctorId, doctorDTO);
        return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
    }
}
