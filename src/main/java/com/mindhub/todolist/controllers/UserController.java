package com.mindhub.todolist.controllers;

import com.mindhub.todolist.dtos.NewUserRecord;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @Operation(summary = "Retrieves all users from the database.", tags = "User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The UserEntity objects from the database.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
            }),
            @ApiResponse(responseCode = "404", description = "No users found.")
    })
    public ResponseEntity<?> getAllUsers(Authentication authentication) {
        Set<UserEntity> users = userService.getAllUsers(authentication.getName());
        return users.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
    }

    @GetMapping("/current")
    @Operation(summary = "Retrieves the current user.", tags = "User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The UserEntity object of the current user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
            }),
            @ApiResponse(responseCode = "404", description = "The current user was not found.")
    })
    public ResponseEntity<?> getCurrentUser(Authentication authentication) {
        if (userService.getAuthenticatedUser(authentication.getName()) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(userService.getAuthenticatedUser(authentication.getName()));
    }

    @PutMapping("/update")
    @Operation(summary = "Updates the current user.", tags = "User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The UserEntity object of the current user.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UserEntity.class))
            }),
            @ApiResponse(responseCode = "404", description = "The current user was not found.")
    })
    public ResponseEntity<?> updateCurrentUser(NewUserRecord newUserRecord, Authentication authentication) {
        if (userService.getAuthenticatedUser(authentication.getName()) == null) {
            return ResponseEntity.notFound().build();
        }
        if(newUserRecord.name() == null || newUserRecord.name().isBlank()) {
            return ResponseEntity.badRequest().body("The name cannot be null or empty.");
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
        return ResponseEntity.ok(userService.getAuthenticatedUser(authentication.getName()));
    }
}
