package com.example.fractobackend.controller;

import com.example.fractobackend.dto.TimeSlotDto;
import com.example.fractobackend.entity.TimeSlot;
import com.example.fractobackend.service.TimeSlotServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
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

    @GetMapping("/time-slots/time/")
    public List<String> findTimeSlotTimeByDoctorAndStatusAndDate(@RequestParam  Long doctorId, @RequestParam  String status, @RequestParam String availableDate) {
        return timeSlotServiceImpl.getTimeSlotTimeByDoctorAndStatusAndDate(doctorId, status, availableDate);
    }

    @PostMapping("/time-slots/id/")
    public List<Long> findTimeSlotIDTimeByDoctorAndStatusAndDateAndTime(@RequestBody TimeSlotDto timeSlotDto) {
        return timeSlotServiceImpl.getTimeSlotIDTimeByDoctorAndStatusAndDateAndTime(timeSlotDto.getDoctorId(), timeSlotDto.getStatus(), timeSlotDto.getAvailableDate(), timeSlotDto.getAvailableTime());
    }
}
