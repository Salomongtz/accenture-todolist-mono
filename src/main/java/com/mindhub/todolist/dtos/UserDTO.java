package com.mindhub.todolist.dtos;

import com.mindhub.todolist.models.UserEntity;

public class UserDTO {

    private Long id;
    private String username, password, email;

    public UserDTO() {
    }

    public UserDTO(UserEntity user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
