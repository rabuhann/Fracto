package com.example.fractobackend.controller;

import com.example.fractobackend.entity.TimeSlot;
import com.example.fractobackend.service.TimeSlotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class TimeSlotController {

    private final TimeSlotServiceImpl timeSlotServiceImpl;

    @Autowired
    public TimeSlotController(TimeSlotServiceImpl timeSlotServiceImpl) {
        this.timeSlotServiceImpl = timeSlotServiceImpl;
    }

    @GetMapping("/time-slots/doctor/{doctorId}")
    public List<TimeSlot> getTimeSlotsByDoctor(@PathVariable Long doctorId) {
        return timeSlotServiceImpl.getTimeSlotsByDoctor(doctorId);
    }

    @GetMapping("/time-slots/date/")
    public List<String> findTimeSlotDateByDoctorAndStatus(@RequestParam  Long doctorId, @RequestParam  String status) {
        return timeSlotServiceImpl.getTimeSlotDateByDoctorAndStatus(doctorId, status);
    }
}
