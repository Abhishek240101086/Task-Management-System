package com.example.taskManagement.repository;

import com.example.taskManagement.entity.Task;
import com.example.taskManagement.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByStatus(Status status);
}
