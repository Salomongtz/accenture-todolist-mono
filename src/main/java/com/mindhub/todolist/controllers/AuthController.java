package com.mindhub.todolist.controllers;

import com.mindhub.todolist.configuration.JwtUtil;
import com.mindhub.todolist.dtos.LoginRequest;
import com.mindhub.todolist.dtos.NewUserRecord;
import com.mindhub.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.username(),
                        loginRequest.password()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication.getName());
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody NewUserRecord newUserRecord) {
        if (newUserRecord == null) {
            return ResponseEntity.badRequest().body("The user cannot be null.");
        }
        if (newUserRecord.name() == null || newUserRecord.name().isBlank()) {
            return ResponseEntity.badRequest().body("The username cannot be null or empty.");
        }
        if (newUserRecord.username().isEmpty() || newUserRecord.username().isBlank()) {
            return ResponseEntity.badRequest().body("The username cannot be empty.");
        }
        if (newUserRecord.password().isEmpty() || newUserRecord.password().isBlank()) {
            return ResponseEntity.badRequest().body("The password cannot be empty.");
        }
        if (newUserRecord.email().isEmpty() || newUserRecord.email().isBlank()) {
            return ResponseEntity.badRequest().body("The email cannot be empty.");
        }
        userService.registerUser(newUserRecord);
        return ResponseEntity.ok("User registered successfully.");
    }
}

