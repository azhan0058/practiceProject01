package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.TaskResponseDto;
import com.example.demo.entity.Task;
import com.example.demo.service.TaskService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;





@RestController
@RequestMapping("/v2/abc")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping("/{id}/task")
    public ResponseEntity<TaskResponseDto> createTask(@RequestBody Task task, @PathVariable Long id) {
        Task currentTask = taskService.createTask(id, task);
        TaskResponseDto TaskDto = taskService.getTaskById(currentTask.getId())
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error mapping task output"));
            
        return ResponseEntity.status(HttpStatus.CREATED).body(TaskDto);
    }

    @PostMapping("/tasks/{taskId}/assign/{userId}")
    public ResponseEntity<Void> assignTask(@PathVariable Long taskId, @PathVariable Long userId) {
        taskService.assignTask(taskId, userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/task")
    public List<TaskResponseDto> getAllTask() {
        return taskService.getAllTask();
    }

    @GetMapping("/task/{id}")
    public TaskResponseDto getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
    }

    @DeleteMapping("/task/{id}")
    public boolean deleteTask(@PathVariable Long id) {
        boolean deleted = taskService.deleteTask(id);
        return deleted;
    }
    
    
    
    
}
