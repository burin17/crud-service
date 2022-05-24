package com.gmail.burinigor7.crudservice.service;

import com.gmail.burinigor7.crudservice.domain.User;
import com.gmail.burinigor7.crudservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User user(Long id) {
        return userRepository.findById(id).get();
    }

    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User getUserByUsername(String username) {
        return userRepository.getUserByUsername(username).get();
    }

    public Map<String, String> validate(User user) {
        Map<String, String> errMap = new HashMap<>();
        if (userRepository.getUserByUsername(user.getUsername()).isPresent()) {
            errMap.put("username", "Specified username is already in use");
        }
        if (userRepository.getUserByEmail(user.getEmail()).isPresent()) {
            errMap.put("email", "Specified email is already in use");
        }
        return errMap;
    }

    public List<User> findByPieceOfUsername(String usernamePiece) {
        return userRepository.getByUsernamePiece(usernamePiece);
    }
}
