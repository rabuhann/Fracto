package com.example.fractobackend.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.fractobackend.dto.AppointmentRequestDto;
import com.example.fractobackend.entity.Appointment;
import com.example.fractobackend.entity.Doctor;
import com.example.fractobackend.entity.TimeSlot;
import com.example.fractobackend.entity.User;
import com.example.fractobackend.exception.ResourceNotFoundException;
import com.example.fractobackend.repository.AppointmentRepository;
import com.example.fractobackend.repository.DoctorRepository;
import com.example.fractobackend.repository.TimeSlotRepository;
import com.example.fractobackend.repository.UserRepository;
import com.example.fractobackend.service.AppointmentServiceImpl;
import com.example.fractobackend.service.EmailSenderService;



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
	private EmailSenderService emailService;
	@Autowired
	private AppointmentRepository appoRepo;
	
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
		//Sending confirmation
		String confirmation = "Appoinment Successfully Scheduled at "+timeSlot.getAvailableTime()+" on "+timeSlot.getAvailableDate();
		emailService.sendEmail(user.getEmail(),"Fracto Appoinment",confirmation);
		
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
        //Send cancellation message
        String cancelMessage = "You have successfully cancelled your appointment";
        emailService.sendEmail(appointment.getUserAppo().getEmail(), "Fracto Appointment Cancellation", cancelMessage);
        return ResponseEntity.ok(response);
    }
	
	@GetMapping("/all-appointments")
	public List<Object[]>  allAppointmentsByUser(@RequestParam(name = "u_id") Long id){
		
		List<Object[]> appointment = appointmentService.getAllAppointmentsByUser(id);
 
        return appointment;
		
	}

}
