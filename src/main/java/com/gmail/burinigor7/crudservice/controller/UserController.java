package com.gmail.burinigor7.crudservice.controller;

import com.gmail.burinigor7.crudservice.domain.User;
import com.gmail.burinigor7.crudservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User user(@PathVariable("userId") Long id) {
        return userService.user(id);
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
