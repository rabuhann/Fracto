package com.example.fractobackend.controller;

import com.example.fractobackend.exception.ResourceNotFoundException;
import com.example.fractobackend.entity.Doctor;
import com.example.fractobackend.repository.DoctorRepository;
import com.example.fractobackend.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class DoctorController {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DoctorService doctorService;

    // Get all doctors
    @GetMapping("/doctors")
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Create doctor Rest API
    @PostMapping("/doctors")
    public Doctor createDoctor(@RequestBody Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // Get doctor by ID Rest API
    @GetMapping("/doctors/{id}")
    public ResponseEntity<Doctor> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor doesn't exists with id: " + id));
        return ResponseEntity.ok(doctor);
    }

    // Update doctor REST API
    @PutMapping("/doctors/{id}")
    public ResponseEntity<Doctor> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor doesn't exists with id: " + id));

        doctor.setDoctorName((doctorDetails.getDoctorName()));
        doctor.setSpecialization(doctorDetails.getSpecialization());
        doctor.setCity(doctorDetails.getCity());
        doctor.setRatings(doctorDetails.getRatings());

        Doctor updatedDoctor = doctorRepository.save(doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    // Delete doctor REST API
    @DeleteMapping("/doctors/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteDoctor(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor doesn't exists with id: " + id));

        doctorRepository.delete(doctor);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/specializations")
    public List<String> getAllSpecializations() {
        return doctorService.getAllSpecializations();
    }

    @GetMapping("/specialization/{specialization}")
    public List<Doctor> getDoctorsBySpecialization(@PathVariable String specialization) {
        // Convert hyphens back to spaces
        specialization = specialization.replace("-", " ");
        return doctorService.getDoctorsBySpecialization(specialization);
    }
}
