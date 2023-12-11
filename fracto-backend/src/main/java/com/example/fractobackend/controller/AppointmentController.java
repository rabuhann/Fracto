package com.example.fractobackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fractobackend.dto.AppointmentRequestDto;
import com.example.fractobackend.entity.Appointment;
import com.example.fractobackend.entity.User;
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
	@PostMapping("/make-appointment")
	public String make_appoinment(@RequestBody AppointmentRequestDto appo, @RequestParam(name = "u_id") Long id) {
		//post api url : http://localhost:8080/api/v1/make-appointment?u_id=user_id
		User user = userRepo.getById(id);
		
		appo.getAppointment().setUserAppo(user);
		appo.getAppointment().setStatus("Active"); //Initially "Active" status
		
		List<Appointment> all_appo= new ArrayList<>();
		
		all_appo.add(appo.getAppointment());
		user.setAppointments(all_appo);
		return appointmentService.makeAppoinment(user);
		//userRepo.save(user);

		
		//Post json body example
//		{
//		    "appointment":{
//		        "appointment_date": "2023-07-12",
//		        "time": "01:30:00",
//		        "city": "Texas",
//		        "doc_id": "2"
//		    }
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

}
