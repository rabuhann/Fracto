package com.example.fractobackend.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "doctor")
public class Doctor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doctorID;

    @Column(name = "DoctorName")
    private String doctorName;

    @Column(name = "Specialization")
    private String specialization;

    @Column(name = "City")
    private String city;

    @Column(name = "Ratings")
    private int ratings;

    public Doctor() {
    }

    public Doctor(Long doctorID, String doctorName, String specialization, String city, int ratings) {
        this.doctorID = doctorID;
        this.doctorName = doctorName;
        this.specialization = specialization;
        this.city = city;
        this.ratings = ratings;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getRatings() {
        return ratings;
    }

    public void setRatings(int ratings) {
        this.ratings = ratings;
    }
}
