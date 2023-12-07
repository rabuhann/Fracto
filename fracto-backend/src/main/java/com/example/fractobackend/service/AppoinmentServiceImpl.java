package com.example.fractobackend.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.fractobackend.dto.AppoinmentRequestDto;
import com.example.fractobackend.entity.User;
import com.example.fractobackend.repository.AppoinmentRepository;
import com.example.fractobackend.repository.UserRepository;

@Service
public class AppoinmentServiceImpl {
	@Autowired
	private AppoinmentRepository appoinmentRepo;
	@Autowired
	private UserRepository userRepo;
	
//	public User makeAppoinment(AppoinmentRequestDto appo) {
//		return userRepo.save(appo.getUserAppo());
//	}
}
