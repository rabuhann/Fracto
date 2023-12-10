package com.example.fractobackend.repository;

import com.example.fractobackend.entity.Role;
import com.example.fractobackend.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByDoctorDoctorId(Long doctorId);}
