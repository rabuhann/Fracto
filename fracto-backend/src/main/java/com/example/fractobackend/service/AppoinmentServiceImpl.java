package com.example.fractobackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fractobackend.dto.AppoinmentRequestDto;
import com.example.fractobackend.entity.Appoinment;
import com.example.fractobackend.entity.User;
import com.example.fractobackend.exception.ResourceNotFoundException;
import com.example.fractobackend.repository.AppoinmentRepository;
import com.example.fractobackend.repository.UserRepository;

@Service
public class AppoinmentServiceImpl {
	
	@Autowired
	private AppoinmentRepository appoRepo;
	@Autowired
	private UserRepository userRepo;
	
	public String makeAppoinment(User appo) {
		 userRepo.save(appo);
		 return "Appoinment Confirmed";
	}
	public Appoinment findById(Long id) {
		return appoRepo.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with id: " + id));
	}
	
	public String cancel(Appoinment appoinment) {
		appoRepo.deleteById(appoinment.getA_id());
		return "Canceled";
	}
}
