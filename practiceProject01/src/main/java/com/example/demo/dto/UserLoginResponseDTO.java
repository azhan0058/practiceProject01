package com.example.demo.dto;
import com.example.demo.enums.Role;
import lombok.Data;

@Data 
public class UserLoginResponseDTO {
    private Long id;
    private String name;
    private String email;
    private Role role;
    private String jwt;
}
