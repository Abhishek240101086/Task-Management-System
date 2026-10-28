package com.example.taskManagement.controller;

import com.example.taskManagement.entity.Task;
import com.example.taskManagement.enums.Status;
import com.example.taskManagement.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {

        this.taskService = taskService;
    }

    @GetMapping
    public List<Task> getTasks(
            @RequestParam(required = false) Status status) {

        if(status == null) {
            return taskService.getAllTasks();
        }
        return taskService.getTaskByStatus(status);
    }

    @GetMapping("/{id}")
    public Task getTaskById(@PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public ResponseEntity<Task> createTask(@Valid @RequestBody Task task) {

        Task createdTask = taskService.createTask(task);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdTask);
    }

    @PutMapping("/{id}")
    public  Task updateTask(
            @PathVariable Long id,
            @Valid @RequestBody Task task) {

        return taskService.updateTask(id, task);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {

        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }
}
