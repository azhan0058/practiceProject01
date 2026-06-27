package com.example.demo.dto;

import java.time.LocalDateTime;
import java.util.List;

public interface TaskResponseDto {
    Long getId();
    String getTitle();
    String getDescription();
    String getPriority();
    String getStatus();
    LocalDateTime getDueDate();
    List<String> getAttachments();
    String getTodoCheckList();
    int getProgress();
    LocalDateTime getCreatedAt();
    LocalDateTime getUpdatedAt();

    
    UserSummaryDto getCreatedBy();

    
    List<UserSummaryDto> getAssignedTo();
}
