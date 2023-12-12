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
}
