package com.nytri.todolist.todolist_api.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "task_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "task_user")
    private User taskUser;

    @Column(name = "task_name")
    private String taskName;

    @Lob
    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "task_datetime_created")
    private Instant taskDatetimeCreated;

    @Column(name = "task_state")
    private Boolean taskState;

    @Column(name = "task_datetime_finished")
    private Instant taskDatetimeFinished;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getTaskUser() {
        return taskUser;
    }

    public void setTaskUser(User taskUser) {
        this.taskUser = taskUser;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Instant getTaskDatetimeCreated() {
        return taskDatetimeCreated;
    }

    public void setTaskDatetimeCreated(Instant taskDatetimeCreated) {
        this.taskDatetimeCreated = taskDatetimeCreated;
    }

    public Boolean getTaskState() {
        return taskState;
    }

    public void setTaskState(Boolean taskState) {
        this.taskState = taskState;
    }

    public Instant getTaskDatetimeFinished() {
        return taskDatetimeFinished;
    }

    public void setTaskDatetimeFinished(Instant taskDatetimeFinished) {
        this.taskDatetimeFinished = taskDatetimeFinished;
    }

}