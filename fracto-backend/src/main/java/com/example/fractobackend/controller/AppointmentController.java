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

import com.example.fractobackend.dto.AppoinmentRequestDto;
import com.example.fractobackend.entity.Appoinment;
import com.example.fractobackend.entity.User;
import com.example.fractobackend.repository.UserRepository;
import com.example.fractobackend.service.AppoinmentServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class AppointmentController {
	
	@Autowired
	private AppoinmentServiceImpl appoinmentService;
	@Autowired
	private UserRepository userRepo;
	@PostMapping("/make-appoinment")
	public String make_appoinment(@RequestBody AppoinmentRequestDto appo, @RequestParam(name = "u_id") Long id) {
		
		User user = userRepo.getById(id);
		
		appo.getAppoinment().setUserAppo(user);
		
		List<Appoinment> all_appo= new ArrayList<>();
		
		all_appo.add(appo.getAppoinment());
		user.setAppointments(all_appo);
		userRepo.save(user);
		return "Appoinment Confirmed";
		
	}
}
