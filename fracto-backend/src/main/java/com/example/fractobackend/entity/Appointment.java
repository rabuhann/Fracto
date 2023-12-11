package com.example.fractobackend.entity;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.*;

@Entity
@Table(name = "appointments")
public class Appointment {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long a_id;

	private String city;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private LocalDate appointment_date;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="HH:mm:ss")
	private LocalTime time;
	
    public LocalDate getAppointment_date() {
		return appointment_date;
	}

	public void setAppoinment_date(LocalDate appointment_date) {
		this.appointment_date = appointment_date;
	}

	private Long doc_id;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User userAppo;
    
    private String status;

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setAppointment_date(LocalDate appointment_date) {
		this.appointment_date = appointment_date;
	}

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
