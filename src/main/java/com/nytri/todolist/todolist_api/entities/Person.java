package com.nytri.todolist.todolist_api.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @Column(name = "person_id", nullable = false)
    private Integer id;

    @Column(name = "person_first_name", length = 64)
    private String personFirstName;

    @Column(name = "person_last_name", length = 64)
    private String personLastName;

    @Column(name = "person_age")
    private Short personAge;

    @Column(name = "person_birthday")
    private LocalDate personBirthday;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPersonFirstName() {
        return personFirstName;
    }

    public void setPersonFirstName(String personFirstName) {
        this.personFirstName = personFirstName;
    }

    public String getPersonLastName() {
        return personLastName;
    }

    public void setPersonLastName(String personLastName) {
        this.personLastName = personLastName;
    }

    public Short getPersonAge() {
        return personAge;
    }

    public void setPersonAge(Short personAge) {
        this.personAge = personAge;
    }

    public LocalDate getPersonBirthday() {
        return personBirthday;
    }

    public void setPersonBirthday(LocalDate personBirthday) {
        this.personBirthday = personBirthday;
    }

}