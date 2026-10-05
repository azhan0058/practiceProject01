package com.example.demo.dto;
import lombok.Data;

@Data 
public class UserSignupRequestDTO {
   private String name;
   private String email;
   private long phoneNumber;
   private String password;
   
}