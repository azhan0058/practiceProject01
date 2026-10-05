package com.example.demo.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
// import org.mapstruct.MappingConstants;

import com.example.demo.dto.UserSignupRequestDTO;
import com.example.demo.entity.User;

@Mapper(componentModel = "spring")
//@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    // @Mapping(source = "emailAddress", target = "email")
    // @Mapping(source = "plainPassword", target = "passwordHash")
    @Mapping(target = "id", ignore = true) // Database handles ID creation
    @Mapping(target = "role", constant = "Member")
    User toEntity(UserSignupRequestDTO requestDto);
    
    // Inverse mapping if you need to return an object back to the client
    // @Mapping(source = "email", target = "emailAddress")
    // @Mapping(source = "passwordHash", target = "plainPassword")
    UserSignupRequestDTO toDto(User entity);

}

