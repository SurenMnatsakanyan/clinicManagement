package com.example.clinicmanagement.controller;
import com.example.clinicmanagement.model.Doctor;
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
    public List<Doctor> getAllDoctors(){
        return doctorService.getDoctors();
    }

    @PostMapping
    public ResponseEntity<Doctor> registerNewDoctor(@Valid @RequestBody Doctor doctor){
        doctorService.addDoctor(doctor);
        return new ResponseEntity<>(doctor, HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{doctorId}")
    public void deleteDoctor(@PathVariable("doctorId") Long doctorId){
        doctorService.deleteDoctorById(doctorId);
    }

    @PutMapping(path = "/{doctorId}")
    public ResponseEntity<Doctor> updateDoctor(@RequestParam("doctorId") Long doctorId,
                                               @RequestBody @Valid Doctor doctor
                                               ){
            doctorService.modifyDoctorInformation(doctorId,doctor);
            return  new ResponseEntity<>(doctor,HttpStatus.OK);
    }
}
