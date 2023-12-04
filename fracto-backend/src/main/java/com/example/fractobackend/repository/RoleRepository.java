package com.example.fractobackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fractobackend.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role findByRole(String role);

}