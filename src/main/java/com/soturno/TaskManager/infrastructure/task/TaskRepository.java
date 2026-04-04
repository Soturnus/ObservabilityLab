package com.soturno.TaskManager.infrastructure.task;

import com.soturno.TaskManager.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TaskRepository extends JpaRepository<Task, UUID> {


}
