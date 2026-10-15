package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.UserLoginRequestDTO;
import com.example.demo.dto.UserLoginResponseDTO;
import com.example.demo.dto.UserResponseDto;
import com.example.demo.dto.UserSignupRequestDTO;
import com.example.demo.dto.UserSignupResponseDTO;
import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;




@RestController
@RequestMapping("/api/v1")
public class UserController{

    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/signUp")
    public ResponseEntity<UserSignupResponseDTO> signUpUser(@RequestBody UserSignupRequestDTO user) {
        UserSignupResponseDTO createdUser= userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser); 
    }

    @PostMapping("/login")
    public UserLoginResponseDTO loginUser(@RequestBody UserLoginRequestDTO user) {
        return userService.loginUser(user);   
    }
    
    @PostMapping("/forgetPassword")
    public void forgetPassword(@RequestBody User request){
        String email = request.getEmail();
        //userService.verifyEmail();
    }
    
    //admin api
    @GetMapping("/user")
    public List<UserResponseDto> getAllUser() {
        return userService.getAllUser();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
       return userService.getUserById(id).map(user -> ResponseEntity.ok(user)).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable Long id){
        userService.deleteUser(id);
    }

    @PutMapping("user/{id}")
    public User updateUserById(@PathVariable Long id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @GetMapping("/health")
    public String health() {
        return "This is health check";
    }


    
}