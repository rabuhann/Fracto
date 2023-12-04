package com.example.fractobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fractobackend.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{
    User findByUserName(String username);
}