package com.example.fractobackend.repository;

import com.example.fractobackend.entity.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByDoctorDoctorId(Long doctorId);

    // SELECT distinct available_date FROM timeslots WHERE doctor_id = 1 AND status = "Available";
    @Query("SELECT DISTINCT t.availableDate FROM TimeSlot t WHERE t.doctor.doctorId = :doctorId AND t.status = :status")
    List<String> findTimeSlotDateByDoctorAndStatus(
            @Param("doctorId") Long doctorId,
            @Param("status") String status
    );

    // SELECT distinct available_time FROM timeslots WHERE doctor_id = 1 AND status = "Available" AND available_date = "12/12/2023";
    @Query("SELECT DISTINCT t.availableTime FROM TimeSlot t WHERE t.doctor.doctorId = :doctorId AND t.status = :status AND t.availableDate = :availableDate")
    List<String> findTimeSlotTimeByDoctorAndStatusAndDate(
            @Param("doctorId") Long doctorId,
            @Param("status") String status,
            @Param("availableDate") String availableDate
    );
}
