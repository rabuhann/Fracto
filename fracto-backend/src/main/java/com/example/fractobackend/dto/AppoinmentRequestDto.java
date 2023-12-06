package com.example.fractobackend.dto;

import com.example.fractobackend.entity.Appoinment;

public class AppoinmentRequestDto {
	private Appoinment appoinment;

	public AppoinmentRequestDto() {
		
	}
	
	public AppoinmentRequestDto(Appoinment appoinment) {
		super();
		this.appoinment = appoinment;
	}

	public Appoinment getAppoinment() {
		return appoinment;
	}

	public void setAppoinment(Appoinment appoinment) {
		this.appoinment = appoinment;
	}

	@Override
	public String toString() {
		return "AppoinmentRequestDto [appoinment=" + appoinment + "]";
	}

	
	
	
	
}
