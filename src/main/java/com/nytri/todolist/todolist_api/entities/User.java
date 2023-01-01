package com.nytri.todolist.todolist_api.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_person")
    private Person userPerson;

    @Column(name = "user_name", length = 64)
    private String userName;

    @Column(name = "user_pass", length = 64)
    private String userPass;

    @Column(name = "user_email", length = 64)
    private String userEmail;

    @Column(name = "user_phone", length = 24)
    private String userPhone;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Person getUserPerson() {
        return userPerson;
    }

    public void setUserPerson(Person userPerson) {
        this.userPerson = userPerson;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

}