package com.example.fractobackend.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.example.fractobackend.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.example.fractobackend.dto.LoginDto;
import com.example.fractobackend.dto.SignupDto;
import com.example.fractobackend.entity.Role;
import com.example.fractobackend.entity.User;
import com.example.fractobackend.repository.RoleRepository;
import com.example.fractobackend.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class AuthController {

	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private RoleRepository roleRepository;


    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Get the authenticated user details
        org.springframework.security.core.userdetails.User userDetails =
                (org.springframework.security.core.userdetails.User) authentication.getPrincipal();

        // Convert the UserDetails to your User entity if needed
        User authenticatedUser = userRepository.findByEmail(userDetails.getUsername());


        // Create a response map with user details and token
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User login successfully!...");
        response.put("user", authenticatedUser);

        return ResponseEntity.ok(response);
    }


    @PostMapping("/signup")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody SignupDto signUpDto) {

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>(Collections.singletonMap("error", "Email is already taken!"), HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setUsername(signUpDto.getUserName());
        user.setEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_USER").get();
        user.setRoles(Collections.singleton(roles));
        System.out.println(user.getRoles());
        userRepository.save(user);

        // Create a response map with user details and token
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully!...");
        response.put("user", user);

        return ResponseEntity.ok(response);
    }

    @GetMapping({"/forAdmin"})
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String forAdmin(){
        return "This URL is only accessible to the admin";
    }

    @GetMapping({"/forUser"})
    @PreAuthorize("hasRole('ROLE_USER')")
    public String forUser(){
        return "This URL is only accessible to the user";
    }
	
}
