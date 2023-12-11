package com.example.fractobackend.repository;

import com.example.fractobackend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    @Query("SELECT DISTINCT d.specialization FROM Doctor d")
    List<String> findAllSpecializations();

    List<Doctor> findBySpecialization(String specialization);
}
