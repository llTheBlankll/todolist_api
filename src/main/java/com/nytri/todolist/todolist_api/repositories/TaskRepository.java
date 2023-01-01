package com.nytri.todolist.todolist_api.repositories;

import com.nytri.todolist.todolist_api.entities.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

    boolean findByIdExists(long task_id);
}