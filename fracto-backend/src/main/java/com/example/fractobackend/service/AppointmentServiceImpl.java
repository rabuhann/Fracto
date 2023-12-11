package com.example.fractobackend.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fractobackend.entity.Appointment;
import com.example.fractobackend.entity.User;
import com.example.fractobackend.exception.ResourceNotFoundException;
import com.example.fractobackend.repository.AppointmentRepository;
import com.example.fractobackend.repository.UserRepository;

@Service
public class AppointmentServiceImpl {
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private UserRepository userRepo;
	
	//Make appointment
	public String makeAppoinment(User appo) {
		
		 userRepo.save(appo);
		 return "Appoinment Confirmed";
	}
	//find appointment by appointment id
	public Appointment findById(Long id) {
		return appointmentRepository.findById(id)
       .orElseThrow(() -> new ResourceNotFoundException("Appointment doesn't exist with id: " + id));
	}
	
	//Cancel appointment
	public String cancel(Appointment appointment) {
		System.out.println(appointment.getAppointmentId());
		appointment.setStatus("Cancelled"); //setting status as "Cancelled"
		appointmentRepository.save(appointment);
		
		return "Canceled";
	}

}
