package com.example.fractobackend.service;

import com.example.fractobackend.entities.Doctor;
import com.example.fractobackend.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor saveDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> fetchDoctorList() {
        return (List<Doctor>) doctorRepository.findAll();
    }

    @Override
    public Doctor update(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    @Override
    public void delete(Doctor doctor) {
        doctorRepository.delete(doctor);
    }

    @Override
    public Optional<Doctor> get(Long doctorId) {
        return doctorRepository.findById(doctorId);
    }
}
