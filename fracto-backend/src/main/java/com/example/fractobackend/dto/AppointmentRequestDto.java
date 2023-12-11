package com.example.fractobackend.dto;

import com.example.fractobackend.entity.Appointment;
import com.example.fractobackend.entity.City;
import com.example.fractobackend.entity.Doctor;

public class AppointmentRequestDto {
	
	private Doctor doctor;
	
	private Long doctor_id;
	private Long timeSlot_id;

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}

	public Long getDoctor_id() {
		return doctor_id;
	}

	public void setDoctor_id(Long doctor_id) {
		this.doctor_id = doctor_id;
	}

	public Long getTimeSlot_id() {
		return timeSlot_id;
	}

	public void setTimeSlot_id(Long timeSlot_id) {
		this.timeSlot_id = timeSlot_id;
	}


	public AppointmentRequestDto() {
		
	}
	

	
	
	
	
}
