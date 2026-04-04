package com.soturno.TaskManager.application.task;

import com.soturno.TaskManager.domain.task.Task;
import com.soturno.TaskManager.infrastructure.task.TaskRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public Task create(String title) {
        Task task = new Task(title);
        return repository.save(task);
    }

    @Cacheable("tasks")
    public List<Task> list() {
        return repository.findAll();
    }
}
