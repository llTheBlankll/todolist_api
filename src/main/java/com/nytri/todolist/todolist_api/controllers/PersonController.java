package com.nytri.todolist.todolist_api.controllers;

import com.nytri.todolist.todolist_api.Constants;
import com.nytri.todolist.todolist_api.entities.Person;
import com.nytri.todolist.todolist_api.repositories.PersonRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonRepository personRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

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
            logger.debug("Person " + person_received.getPersonFirstName() + ", " + person_received.getPersonLastName() + " was added.");
        }
    }

    @PostMapping("/update/{id}")
    public void updatePerson(@PathVariable("id") int personId, @RequestBody Person person_received) {
        // Retrieve the person from the database.
        Optional<Person> person = this.personRepository.findById(personId);

        if (person.isPresent()) {
            if (validatePerson(person.get()) && validatePerson(person_received)) {
                this.personRepository.save(person_received);
                logger.debug("Person " + person_received.getPersonFirstName() + ", " + person_received.getPersonLastName() + " was added.");
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletePersonById(@PathVariable("id") int personId) {
        if (this.personRepository.existsById(personId)) {
            this.personRepository.deleteById(personId);
            logger.debug("Person with an ID of " + personId + " was successfully deleted.");
        }

        logger.debug("Person with an ID of " + personId + " was not found.");
    }

    @DeleteMapping("/delete")
    public void deletePerson(@RequestBody Person person) {
        if (validatePerson(person)) {
            if (this.personRepository.existsById(person.getId())) {
                this.personRepository.delete(person);
            }
        }
    }

    // * SEARCH

    @GetMapping("/search/first_name/{first_name}")
    public List<Person> searchByFirstName(@PathVariable("first_name") String firstName) {
        if (firstName.length() > Constants.PERSON_FIRSTNAME_MAX_LENGTH) {
            return this.personRepository.findByPersonFirstNameContains(firstName);
        }

        return Collections.emptyList();
    }

    @GetMapping("/search/last_name/{last_name}")
    public List<Person> searchByLastName(@PathVariable("last_name") String lastName) {
        if (lastName.length() > Constants.PERSON_LASTNAME_MAX_LENGTH) {
            return this.personRepository.findByPersonLastnameContains(lastName);
        }

        return Collections.emptyList();
    }
}
