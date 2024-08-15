package com.mindhub.todolist.services.implementations;

import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImplementation {
    @Autowired
    UserRepository userRepository;

    public UserEntity getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public Set<UserEntity> getAllUsers() {
        return new HashSet<>(userRepository.findAll());
    }

    public UserEntity getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
