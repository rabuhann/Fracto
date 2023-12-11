package com.example.fractobackend.service;

import com.example.fractobackend.entity.TimeSlot;
import com.example.fractobackend.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeSlotServiceImpl {

    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    public List<TimeSlot> getTimeSlotsByDoctor(Long doctorId) {
        return timeSlotRepository.findByDoctorDoctorId(doctorId);
    }
}