package com.example.demo.dto;

import java.time.LocalDateTime;

import com.example.demo.enums.Role;
import lombok.Data;

@Data
public class UserSignupResponseDTO {
   private String name;
   private String email;
   private long phoneNumber;
   private String password;
   private Role role;
   private LocalDateTime createdAt;
   private String JWT = "123458432962560356205602";
}