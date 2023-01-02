package com.nytri.todolist.todolist_api.controllers;

import com.nytri.todolist.todolist_api.entities.User;
import com.nytri.todolist.todolist_api.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private boolean validateUser(User user) {
        if (user.getUserName().length() > 64) {
            return false;
        }

        if (user.getUserPass().length() > 64) {
            return false;
        }

        if (user.getUserEmail().length() > 64) {
            return false;
        }

        return user.getUserPhone().length() <= 24;
    }

    @GetMapping("/all")
    public List<User> getAlLUsers() {
        return this.userRepository.findAll();
    }

    @PutMapping("/add")
    public void addUser(@RequestBody User user) {
        if (validateUser(user)) {
            this.userRepository.save(user);
            logger.debug("User " + user.getId() + " successfully saved.");
        }
    }

    @PostMapping("/update")
    public void updateUser(@RequestBody User user) {
        if (validateUser(user)) {
            if (this.userRepository.existsById(user.getId())) {
                this.userRepository.save(user);
                logger.debug("User " + user.getId() + " was successfully updated.");
            }
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUserById(@PathVariable("id") int user_id) {
        if (this.userRepository.existsById(user_id)) {
            this.userRepository.deleteById(user_id);
            logger.debug("User " + user_id + " was successfully deleted.");
        }
    }

    @DeleteMapping("/delete")
    public void deleteUser(@RequestBody User user) {
        if (this.userRepository.existsById(user.getId())) {
            this.userRepository.delete(user);
            logger.debug("User " + user.getId() + " was successfully deleted.");
        }
    }
}
