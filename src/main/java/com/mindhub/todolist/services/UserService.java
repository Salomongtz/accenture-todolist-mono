package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.NewUserRecord;
import com.mindhub.todolist.models.UserEntity;

import java.util.Set;

public interface UserService {
    void registerUser(NewUserRecord newUserRecord);

    Set<UserEntity> getAllUsers();

    UserEntity getAuthenticatedUser(String username);
}
