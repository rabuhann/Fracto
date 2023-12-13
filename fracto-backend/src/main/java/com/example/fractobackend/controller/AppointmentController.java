package com.example.fractobackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.fractobackend.dto.TimeSlotDto;
import com.example.fractobackend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.fractobackend.dto.AppointmentRequestDto;
import com.example.fractobackend.entity.Appointment;
import com.example.fractobackend.entity.Doctor;
import com.example.fractobackend.entity.TimeSlot;
import com.example.fractobackend.entity.User;
import com.example.fractobackend.exception.ResourceNotFoundException;
import com.example.fractobackend.repository.DoctorRepository;
import com.example.fractobackend.repository.TimeSlotRepository;
import com.example.fractobackend.repository.UserRepository;
import com.example.fractobackend.service.AppointmentServiceImpl;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class AppointmentController {
	
	@Autowired
	private AppointmentServiceImpl appointmentService;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private DoctorRepository doctorRepository;
	@Autowired
	private TimeSlotRepository timeSlotRepository;
	@Autowired
	private AppointmentServiceImpl appointmentServiceImpl;
	
	@PostMapping("/make-appointment")
	public String make_appoinment(@RequestBody AppointmentRequestDto appo, @RequestParam(name = "u_id") Long id) {
		//post api url : http://localhost:8080/api/v1/make-appointment?u_id=user_id
		User user = userRepo.getById(id);
		
		Appointment appointment = new Appointment();
		appointment.setUserAppo(user);
		
		Doctor doctor = doctorRepository.findById(appo.getDoctor_id())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor doesn't exists with id: " + id));
		
		TimeSlot timeSlot = timeSlotRepository.findById(appo.getTimeSlot_id())
                .orElseThrow(() -> new ResourceNotFoundException("Time slot doesn't exists with id: " + appo.getTimeSlot_id()));
		
		appointment.setDoctor(doctor);
		appointment.setTimeSlot(timeSlot);
		appointment.setStatus("Active");

				
		List<Appointment> all_appo= new ArrayList<>();
		
		all_appo.add(appointment);
		user.setAppointments(all_appo);
		return appointmentService.makeAppoinment(user);
		
		//Post json body example
//		{
//		    "doctor_id":"1",
//        	"timeSlot_id": "1"
//		    
//		}
//		
		
	}


	// api/v1/appointment/{appointment_id}
	@PutMapping("/appointment/{id}")
    public ResponseEntity<Map<String, Boolean>> cancel_appoinment(@PathVariable Long id) {
        Appointment appointment = appointmentService.findById(id);
        String msg = appointmentService.cancel(appointment);
        Map<String, Boolean> response = new HashMap<>();
        response.put(msg, Boolean.TRUE);
      
        return ResponseEntity.ok(response);
    }

	@PostMapping ("/appointments")
	public List<Object[]> findAppointmentsByUserId(@RequestParam(name = "userId") Long userId) {
		return appointmentServiceImpl.getAppointmentsByUserId(userId);
	}
}