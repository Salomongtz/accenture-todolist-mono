package com.mindhub.todolist.models;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class UserEntity {
    @Enumerated(EnumType.STRING)
    private RoleType role = RoleType.USER;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name, password;
    @Column(unique = true)
    private String email;
    @Column(unique = true)
    private String username;
    @OneToMany(mappedBy = "user")
    private Set<Task> tasks = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(String name, String password, String email, String username) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.username = username;
    }

    public void addTask(Task task) {
        task.setUser(this);
        this.tasks.add(task);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public RoleType getRole() {
        return role;
    }

    public void setRole(RoleType role) {
        this.role = role;
    }
}
