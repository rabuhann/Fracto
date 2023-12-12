package com.example.fractobackend.controller;

import com.example.fractobackend.dto.SignupDto;
import com.example.fractobackend.entity.Role;
import com.example.fractobackend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.fractobackend.exception.ResourceNotFoundException;
import com.example.fractobackend.entity.User;
import com.example.fractobackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    // Get all users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Create user Rest API
    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {

        // add check for email exists in DB
        if(userRepository.existsByEmail(user.getEmail())){
            return new ResponseEntity<>(Collections.singletonMap("error", "Email is already taken!"), HttpStatus.BAD_REQUEST);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);

        // Create a response map with user details and token
        Map<String, Object> response = new HashMap<>();
        response.put("message", "User registered successfully!...");
        response.put("user", user);

        return ResponseEntity.ok(response);
    }

    // Get user by ID Rest API
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exists with id: " + id));
        return ResponseEntity.ok(user);
    }

    // Update user REST API
    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exists with id: " + id));

        user.setUsername((userDetails.getUsername()));
        user.setEmail(userDetails.getEmail());
        user.setRoles(userDetails.getRoles());
        user.setPassword(passwordEncoder.encode(userDetails.getPassword()));

        User updatedUser = userRepository.save(user);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete user REST API
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User doesn't exist with id: " + id));

        // Remove associations with roles
        user.setRoles(null); // or user.getRoles().clear();

        userRepository.delete(user);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
