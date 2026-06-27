package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

public interface UserResponseDto {
    Long getId();
    String getName();
    String getEmail();
    String getUserName();
    long getPhoneNumber();
    String getRole(); 
    LocalDateTime getCreatedAt();

    List<TaskSummaryDto> getCreatedTasks();
    List<TaskSummaryDto> getAssignedTasks();
}
    

