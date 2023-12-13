package com.example.fractobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fractobackend.entity.Appointment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long>{

	//Appointment save(User userAppointment);

	//Appointment save(List<Appointment> appointments);

	/*
	SELECT d.doctor_name, t.available_date, t.available_time
	FROM appointments a JOIN doctor d ON a.doctor_id = d.doctor_id
	JOIN timeslots t ON a.timeslot_id = t.timeslot_id
	WHERE a.user_id = 3;
	 */

    @Query("SELECT d.doctorName, t.availableDate, t.availableTime " +
            "FROM Appointment a " +
            "JOIN Doctor d ON a.doctor.doctorId = d.doctorId " +
            "JOIN TimeSlot t ON a.timeSlot.timeslotId = t.timeslotId " +
            "WHERE a.userAppo = :userId")
    List<Object[]> findAppointmentsByUserId(@Param("userId") Long userId);

}
