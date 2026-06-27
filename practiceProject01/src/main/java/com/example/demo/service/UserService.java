package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.UserResponseDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

@Service
public class UserService {
        
        
    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        if(user == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User can't be null");
        }
        return userRepository.save(user);
    }

    public List<UserResponseDto> getAllUser(){
        return userRepository.findProjectedBy();
    }

    public Optional<UserResponseDto> getUserById(Long id) {
    if (id == null) {
        return Optional.empty();
    } 
    return userRepository.findProjectedById(id);
}

    public boolean deleteUser(Long id) {
       if (id == null) {
        return false;
       }
       if (!userRepository.existsById(id)) {
        return false;
       }
    userRepository.deleteById(id);
    return true;
    }

    public User updateUser(Long id, User user){
        if (id == null) {
        throw new ResponseStatusException (HttpStatus.BAD_REQUEST,"The provided ID cannot be null");
        }

        User currentUser = userRepository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found with id "+id));

        currentUser.setName(user.getName());
        currentUser.setEmail(user.getEmail());
        currentUser.setUserName(user.getUserName());
        currentUser.setPhoneNumber(user.getPhoneNumber());
        currentUser.setPassword(user.getPassword());

        return userRepository.save(currentUser);

    }

    public User loginUser(String email, String password){

        User currentUser = userRepository.findByEmail(email).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found with email: " + email));       
        if(!password.equals(currentUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,"Invalid Password " ); 
        }
        return currentUser;
    }
}
