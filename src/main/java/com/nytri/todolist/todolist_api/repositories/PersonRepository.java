package com.nytri.todolist.todolist_api.repositories;

import com.nytri.todolist.todolist_api.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Integer> {
    boolean existsById(int personId);
    List<Person> findByPersonFirstNameContains(String personFirstName);
    List<Person> findByPersonLastnameContains(String personLastName);
}