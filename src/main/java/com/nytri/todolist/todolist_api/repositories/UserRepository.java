package com.nytri.todolist.todolist_api.repositories;

import com.nytri.todolist.todolist_api.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}