package com.example.fractobackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fractobackend.dto.AppointmentRequestDto;
import com.example.fractobackend.entity.Appointment;
import com.example.fractobackend.entity.User;
import com.example.fractobackend.repository.UserRepository;
import com.example.fractobackend.service.AppointmentServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class AppointmentController {
	
	@Autowired
	private AppointmentServiceImpl appointmentService;
	@Autowired
	private UserRepository userRepo;
	@PostMapping("/make-appointment")
	public String make_appointment(@RequestBody AppointmentRequestDto appo, @RequestParam(name = "u_id") Long id) {
		
		User user = userRepo.getById(id);
		
		appo.getAppointment().setUser(user);
		
		List<Appointment> all_appo= new ArrayList<>();
		
		all_appo.add(appo.getAppointment());
		user.setAppointments(all_appo);
		userRepo.save(user);
		return "Appointment Confirmed";
		
	}
}
