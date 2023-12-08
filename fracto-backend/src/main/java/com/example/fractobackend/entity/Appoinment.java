package com.example.fractobackend.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Appoinment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long a_id;

	private String city;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate appoinment_date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm:ss")
	private LocalTime time;
	
    public LocalDate getAppoinment_date() {
		return appoinment_date;
	}

	public void setAppoinment_date(LocalDate appoinment_date) {
		this.appoinment_date = appoinment_date;
	}

	private Long doc_id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userAppo;

	public Long getA_id() {
		return a_id;
	}

	public void setA_id(Long a_id) {
		this.a_id = a_id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Long getDoc_id() {
		return doc_id;
	}

	public void setDoc_id(Long doc_id) {
		this.doc_id = doc_id;
	}

	public User getUserAppo() {
		return userAppo;
	}

	public void setUserAppo(User userAppo) {
		this.userAppo = userAppo;
	}
    

	
    
	
    
	
}
