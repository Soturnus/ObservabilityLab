package com.soturno.TaskManager.adapter.in.task;

import com.soturno.TaskManager.domain.task.Task;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @GetMapping
    public String listUser() {
        return "🏍️";
    }
}
