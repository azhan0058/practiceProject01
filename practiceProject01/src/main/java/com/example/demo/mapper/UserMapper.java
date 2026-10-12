package com.example.demo.mapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.example.demo.dto.UserSignupRequestDTO;
import com.example.demo.dto.UserSignupResponseDTO;
import com.example.demo.entity.User;

//@Mapper(componentModel = "spring")
@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", ignore = true) // Database handles ID creation
    //@Mapping(target = "role", constant = "Member")
    User toEntity(UserSignupRequestDTO requestDto);

    UserSignupRequestDTO toRequestDto(User entity);

    UserSignupResponseDTO toResponseDto(User entity);
}

