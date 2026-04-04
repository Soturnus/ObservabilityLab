package com.soturno.TaskManager.adapter.in.task;

import com.soturno.TaskManager.application.task.TaskService;
import com.soturno.TaskManager.application.task.dto.CreateTaskRequest;
import com.soturno.TaskManager.domain.task.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @PostMapping
    public Task create(@RequestBody CreateTaskRequest request) {
        return service.create(request.getTitle());
    }

    @GetMapping
    public List<Task> list() {
        return service.list();
    }
}
