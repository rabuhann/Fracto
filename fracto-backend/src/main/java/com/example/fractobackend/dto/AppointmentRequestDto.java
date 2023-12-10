package com.example.fractobackend.dto;

import com.example.fractobackend.entity.Appointment;

public class AppointmentRequestDto {
	private Appointment appointment;

	public AppointmentRequestDto() {
		
	}
	
	public AppointmentRequestDto(Appointment appointment) {
		super();
		this.appointment = appointment;
	}

	public Appointment getAppointment() {
		return appointment;
	}

	public void setAppointment(Appointment appointment) {
		this.appointment = appointment;
	}

	@Override
	public String toString() {
		return "AppointmentRequestDto [appointment=" + appointment + "]";
	}

	
	
	
	
}
