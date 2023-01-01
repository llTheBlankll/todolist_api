package com.nytri.todolist.todolist_api.controllers;

import com.nytri.todolist.todolist_api.entities.Person;
import com.nytri.todolist.todolist_api.repositories.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonRepository personRepository;

    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public boolean validatePerson(Person person) {
        // Check the data if it is higher than 64 bits, if it is, return false.
        if (person.getPersonFirstName().length() > 64) {
            return false;
        }

        return person.getPersonLastName().length() <= 64;
    }

    @GetMapping("/all")
    public List<Person> getAllPeople() {
        // Return all data.
        return this.personRepository.findAll();
    }

    @PutMapping("/add")
    public void addPerson(@RequestBody Person person_received) {
        // Check if all the inputs are valid.
        if (validatePerson(person_received)) {
            this.personRepository.save(person_received);
        }
    }

    @PostMapping("/update/{id}")
    public void updatePerson(@PathVariable("id") int personId, @RequestBody Person person_received) {
        // Retrieve the person from the database.
        Optional<Person> person = this.personRepository.findById(personId);

        if (person.isPresent()) {
            if (validatePerson(person.get()) && validatePerson(person_received)) {
                this.personRepository.save(person_received);
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletePerson(@PathVariable int personId) {
        this.personRepository.deleteById(personId);
    }
}
