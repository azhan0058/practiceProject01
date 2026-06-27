package com.example.demo.dto;

import java.time.LocalDateTime;

public interface TaskSummaryDto {
    Long getId();
    String getTitle();
    String getStatus();
    String getPriority();
    LocalDateTime getDueDate();
    
}
