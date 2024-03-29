package com.example.fractobackend.service;

import com.example.fractobackend.entity.Appointment;
import com.example.fractobackend.entity.User;
import com.example.fractobackend.exception.ResourceNotFoundException;

public interface AppointmentService {
    //Make appointment
    public String makeAppoinment(User appo);

    //find appointment by appointment id
    public Appointment findById(Long id);

    //Cancel appointment
    public String cancel(Appointment appointment);
}
