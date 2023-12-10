package com.example.fractobackend.service;

import com.example.fractobackend.entity.TimeSlot;

import java.util.List;

public interface TimeSlotService {
    public List<TimeSlot> getTimeSlotsByDoctor(Long doctorId);
}
