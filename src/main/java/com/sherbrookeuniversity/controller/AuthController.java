package com.sherbrookeuniversity.controller;

import com.sherbrookeuniversity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public ResponseEntity<Object> login(
            @RequestParam String email,
            @RequestParam String password) {

        return userRepository.findByEmail(email)
                .filter(user -> password.equals(user.getPassword()))
                .<ResponseEntity<Object>>map(ResponseEntity::ok)
                .orElseGet(() ->
                        ResponseEntity.status(401)
                                .body("Email ou mot de passe incorrect"));
    }

}