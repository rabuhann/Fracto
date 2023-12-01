package com.example.fractobackend.controller;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	//@Autowired
	//private UserService userService;
	@Autowired
    private UserRepository userRepository;
	
	@Autowired
    private AuthenticationManager authenticationManager;
	@Autowired
    private PasswordEncoder passwordEncoder;
	@Autowired
    private RoleRepository roleRepository;
		
	
    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        return new ResponseEntity<>("User login successfully!...", HttpStatus.OK);
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupDto signUpDto){

        // add check for email exists in DB
        if(userRepository.existsByEmail(signUpDto.getEmail())){
            return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
        }

        // create user object
        User user = new User();
        user.setUserName(signUpDto.getUserName());
        user.setUserEmail(signUpDto.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        user.setRoles(Collections.singleton(roles));
        System.out.println(user.getRoles());
        userRepository.save(user);

        return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

    }
	
	
}
