package com.example.fractobackend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.fractobackend.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
//	Optional<User> findByEmail(String userEmail);
//	Optional<User> findByUsernameOrEmail(String userName, String userEmail);
//	User findByUsernameOrEmail(String username, String email);
//	Optional<User> findByUsername(String userName);
//	Boolean existsByUsername(String userName);
	User findByEmail(String email);
    Boolean existsByEmail(String userEmail);

}
