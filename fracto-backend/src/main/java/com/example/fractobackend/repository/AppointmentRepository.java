package com.example.fractobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fractobackend.entity.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

	//Appointment save(User userAppointment);

	//Appointment save(List<Appointment> appointments);

}
