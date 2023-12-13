package com.example.fractobackend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fractobackend.entity.Appointment;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

	
	@Query(value = "SELECT d.doctor_name,d.specialization, t.available_date,t.available_time,a.status FROM appointments a JOIN doctor d ON a.doctor_id = d.doctor_id JOIN timeslots t ON a.timeslot_id = t.timeslot_id WHERE a.user_id=:value",nativeQuery = true)
	List<Object[]> findAppointmentByUser(@Param("value") Long id);

	/*
	SELECT d.doctor_name, t.available_date, t.available_time
	FROM appointments a JOIN doctor d ON a.doctor_id = d.doctor_id
	JOIN timeslots t ON a.timeslot_id = t.timeslot_id
	WHERE a.user_id = 3;
	 */


}

