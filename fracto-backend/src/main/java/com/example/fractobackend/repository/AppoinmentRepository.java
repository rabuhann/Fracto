package com.example.fractobackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fractobackend.entity.Appoinment;
import com.example.fractobackend.entity.User;

public interface AppoinmentRepository extends JpaRepository<Appoinment,Long>{

	//Appoinment save(User userAppoinment);

	//Appoinment save(List<Appoinment> appointments);

}
