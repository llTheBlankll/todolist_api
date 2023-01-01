package com.nytri.todolist.todolist_api.controllers;

import com.nytri.todolist.todolist_api.entities.Task;
import com.nytri.todolist.todolist_api.repositories.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskRepository taskRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public boolean validateTask(Task task) {
        if (task.getTaskName().length() > 255) {
            return false;
        }

        return task.getTaskDescription().length() <= 65535;
    }

    @GetMapping("/all")
    public List<Task> getAllTasks() {
        return this.taskRepository.findAll();
    }

    @PutMapping("/add")
    public void addTask(@RequestBody Task task) {
        if (validateTask(task)) {
            this.taskRepository.save(task);
            logger.debug("Task ID " + task.getId() + " successfully added");
        }
    }

    @DeleteMapping("/delete")
    public void deleteTask(@RequestBody Task task) {
        if (validateTask(task)) {
            this.taskRepository.delete(task);
            logger.debug("Task ID " + task.getId() + " successfully deleted");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteTaskById(@PathVariable("id") long taskId) {
        if (this.taskRepository.findByIdExists(taskId)) {
            this.taskRepository.deleteById(taskId);
            logger.debug("Task Id " + taskId + " successfully deleted");
        }
    }
}
