package com.example.fractobackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fractobackend.repository.AppointmentRepository;
import com.example.fractobackend.repository.UserRepository;

@Service
public class AppointmentServiceImpl {
	@Autowired
	private AppointmentRepository appointmentRepository;
	@Autowired
	private UserRepository userRepo;
	
//	public User makeAppointment(AppointmentRequestDto appo) {
//		return userRepo.save(appo.getUserAppo());
//	}
}
