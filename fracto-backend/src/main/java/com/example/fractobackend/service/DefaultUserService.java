package com.example.fractobackend.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.example.fractobackend.entity.User;
import com.example.fractobackend.entity.UserDTO;

public interface DefaultUserService extends UserDetailsService{
    User save(UserDTO userRegisteredDTO);

}