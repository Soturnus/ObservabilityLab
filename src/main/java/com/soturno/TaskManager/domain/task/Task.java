package com.soturno.TaskManager.domain.task;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.util.UUID;

@Entity
public class Task {

    @Id
    private UUID id;
    private String title;
    private boolean completed;

    protected Task() {}

    public Task(String title) {
        this.id = UUID.randomUUID();
        this.title = title;
        this.completed = false;
    }

    public void complete() {
        this.completed = true;
    }

    public UUID getId() { return id; }
    public String getTitle() { return title; }
    public boolean isCompleted() { return completed; }
}
