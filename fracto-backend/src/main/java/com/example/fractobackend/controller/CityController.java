package com.example.fractobackend.controller;

import com.example.fractobackend.entity.City;
import com.example.fractobackend.repository.CityRepository;
import com.example.fractobackend.repository.DoctorRepository;
import com.example.fractobackend.service.CityServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class CityController {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private DoctorRepository doctorRepository; // Assuming you have a DoctorRepository

    // Get all cities
    @GetMapping("/cities")
    public List<City> getAllCities() {
        return cityRepository.findAll();
    }

    // Get specializations by city
    @GetMapping("/city-specializations/{cityId}")
    public List<String> getSpecializationsByCity(@PathVariable Long cityId) {
        List<String> specializations = doctorRepository.findSpecializationsByCity(cityId);
        return specializations;
    }
}
