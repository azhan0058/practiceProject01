package com.example.demo.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dto.TaskResponseDto;
import com.example.demo.entity.Task;
import java.util.List;
import java.util.Optional;




public interface TaskRepository extends JpaRepository<Task, Long>{

    //List<Task> findByCreatedById(Long id);
    //List<Task> findByAssignedToId(Long id);

    Optional<TaskResponseDto> findProjectedById(Long id);
    List<TaskResponseDto> findProjectedBy();
    List<TaskResponseDto> findProjectedByCreatedById(Long id);
    List<TaskResponseDto> findProjectedByAssignedToId(Long id);
    
}
