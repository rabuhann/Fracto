package com.example.fractobackend.repository;

import com.example.fractobackend.entity.City;
import com.example.fractobackend.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query("SELECT DISTINCT d.specialization FROM Doctor d")
    List<String> findAllSpecializations();

    List<Doctor> findBySpecialization(String specialization);

    @Query("SELECT DISTINCT d.specialization FROM Doctor d WHERE d.city.cityId = :cityId")
    List<String> findSpecializationsByCity(@Param("cityId") Long cityId);

    @Query("SELECT DISTINCT d.ratings FROM Doctor d WHERE d.specialization = :specialization AND d.city.cityId = :cityId")
    List<Integer> findRatingsBySpecializationAndCity(
            @Param("specialization") String specialization,
            @Param("cityId") Long cityId
    );

    // SELECT doctor_name FROM doctor WHERE specialization = 'Family Medicine' AND city_id = 1 AND ratings = 5;
    @Query("SELECT d FROM Doctor d WHERE d.specialization = :specialization AND d.city.cityId = :cityId AND d.ratings = :ratings")
    List<Doctor> findDoctorBySpecializationAndCityAndRating(
            @Param("specialization") String specialization,
            @Param("cityId") Long cityId,
            @Param("ratings") int ratings
    );
}
