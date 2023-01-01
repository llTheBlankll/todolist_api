package com.nytri.todolist.todolist_api.repositories;

import com.nytri.todolist.todolist_api.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {
}