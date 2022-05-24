package com.gmail.burinigor7.crudservice.controller;

import com.gmail.burinigor7.crudservice.domain.User;
import com.gmail.burinigor7.crudservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    @GetMapping
    public ResponseEntity<User> userByUsername(@RequestParam String username) {
        return ResponseEntity.ok().body(userService.getUserByUsername(username));
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/validate")
    public Map<String, String> validate(@RequestBody User registrationForm) {
        return userService.validate(registrationForm);
    }

    @GetMapping("/piece")
    public List<User> usersByPieceOfUsername(@RequestParam String usernamePiece) {
        return userService.findByPieceOfUsername(usernamePiece);
    }
}
