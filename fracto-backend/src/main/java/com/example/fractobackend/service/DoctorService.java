package com.example.fractobackend.service;


import com.example.fractobackend.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Doctor saveDoctor(Doctor product);
    List<Doctor> fetchDoctorList();
    Doctor update(Doctor product);
    void delete(Doctor product);
    Optional<Doctor> get(Long productId);
    List<String> getAllSpecializations();
    List<Doctor> getDoctorsBySpecialization(String specialization);
}
