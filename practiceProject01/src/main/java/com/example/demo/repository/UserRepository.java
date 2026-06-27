package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.User;


public interface UserRepository extends JpaRepository <User, Long > {

    Optional<User> findByEmail(String email);

    Optional<UserResponseDto> findProjectedById(Long id);

    List<UserResponseDto> findProjectedBy();

    
    
}
