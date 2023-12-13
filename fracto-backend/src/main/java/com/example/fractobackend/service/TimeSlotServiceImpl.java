package com.example.fractobackend.service;

import com.example.fractobackend.entity.TimeSlot;
import com.example.fractobackend.repository.TimeSlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.util.List;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    @Autowired
    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository) {
        this.timeSlotRepository = timeSlotRepository;
    }

    @Override
    public List<TimeSlot> getTimeSlotsByDoctor(Long doctorId) {
        return timeSlotRepository.findByDoctorDoctorId(doctorId);
    }

    public List<String> getTimeSlotDateByDoctorAndStatus(Long doctorId, String status) {
        return timeSlotRepository.findTimeSlotDateByDoctorAndStatus(doctorId, status);
    }

    public List<String> getTimeSlotTimeByDoctorAndStatusAndDate(Long doctorId, String status, String availableDate) {
        return timeSlotRepository.findTimeSlotTimeByDoctorAndStatusAndDate(doctorId, status, availableDate);
    }

    public List<Long> getTimeSlotIDTimeByDoctorAndStatusAndDateAndTime(Long doctorId, String status, String availableDate, String availableTime) {
        return timeSlotRepository.findTimeSlotIDTimeByDoctorAndStatusAndDateAndTime(doctorId, status, availableDate, availableTime);
    }
}