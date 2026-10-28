package com.example.taskManagement.service;

import com.example.taskManagement.entity.Task;
import com.example.taskManagement.enums.Status;
import com.example.taskManagement.exception.TaskNotFoundException;
import com.example.taskManagement.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {

        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    public List<Task> getTaskByStatus(Status status) {
        return taskRepository.findByStatus(status);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existingTask = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found"));

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setPriority(updatedTask.getPriority());
        existingTask.setStatus(updatedTask.getStatus());
        existingTask.setDueDate(updatedTask.getDueDate());

        return taskRepository.save(existingTask);
    }

    public void deleteTask(Long id) {
        if(!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Task not found");
        }
        taskRepository.deleteById(id);
    }
}
