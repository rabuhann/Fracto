package com.example.fractobackend.service;


import com.example.fractobackend.entity.Doctor;

import java.util.List;
import java.util.Optional;

public interface DoctorService {
    Doctor saveDoctor(Doctor product);
    List<Doctor> fetchDoctorList();
    public Doctor update(Doctor product);
    public void delete(Doctor product);
    public Optional<Doctor> get(Long productId);
}
