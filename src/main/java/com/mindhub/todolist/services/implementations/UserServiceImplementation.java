package com.mindhub.todolist.services.implementations;

import com.mindhub.todolist.dtos.NewUserRecord;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.UserRepository;
import com.mindhub.todolist.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImplementation implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptPasswordEncoder;

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Set<UserEntity> getAllUsers() {
        return new HashSet<>(userRepository.findAll());
    }

    @Override
    public UserEntity getAuthenticatedUser(String username) {
        if(username == null || username.isBlank()|| username.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (userRepository.findByUsername(username).isPresent()) {
            return userRepository.findByUsername(username).get();
        }
        return null;
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    @Override
    public void registerUser(NewUserRecord newUserRecord) {
        if (userRepository.findByUsername(newUserRecord.username()).isPresent()) {
            throw new IllegalArgumentException("Username already in use");
        }
        if (userRepository.findByEmail(newUserRecord.email()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        UserEntity user = new UserEntity(newUserRecord.name(), bcryptPasswordEncoder.encode(newUserRecord.password()), newUserRecord.email(), newUserRecord.username());
        userRepository.save(user);
    }
}
