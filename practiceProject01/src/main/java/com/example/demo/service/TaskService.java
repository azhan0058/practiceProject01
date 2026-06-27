package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.dto.TaskResponseDto;
import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repository.TaskRepository;
import com.example.demo.repository.UserRepository;


@Service
public class TaskService {
    
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;

    public TaskService(TaskRepository taskRepository, UserRepository userRepository){
        this.taskRepository= taskRepository;
        this.userRepository= userRepository;
    }

    public Task createTask(Long id,  Task task){
        if (id == null) {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User ID cannot be null");
        }
        User user = userRepository.findById(id)
                    .orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND,"Invalid userId"));
        task.setCreatedBy(user);
                 task.setAssignedTo(new ArrayList<>());
          return taskRepository.save(task);
    }

    public void assignTask(Long taskId, Long userId) {
        if (taskId == null || userId == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task ID and User ID cannot be null");
        }
        Task task = taskRepository.findById(taskId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Task not found"));
        User user = userRepository.findById(userId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));
         if (!task.getAssignedTo().contains(user)) {
             task.getAssignedTo().add(user);        
             user.getAssignedTasks().add(task);    
         }
    }
       
    public Optional<TaskResponseDto> getTaskById(Long id){
        if (id == null) {
            return Optional.empty();
        }
        return taskRepository.findProjectedById(id);
    }       

    public List<TaskResponseDto> getAllTask(){
        return taskRepository.findProjectedBy();
    }   


    public void updateTask(){}
    public boolean deleteTask(Long id){
        if (id == null || !taskRepository.existsById(id)) {
            return false;
        }
        taskRepository.deleteById(id);
        return true;
    }   

}
