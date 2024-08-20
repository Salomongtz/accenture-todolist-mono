package com.mindhub.todolist;

import com.mindhub.todolist.models.RoleType;
import com.mindhub.todolist.models.Task;
import com.mindhub.todolist.models.TaskStatus;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.TaskRepository;
import com.mindhub.todolist.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class Utils {
    @Autowired
    PasswordEncoder bcryptPasswordEncoder;
    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, TaskRepository taskRepository) {
        return args -> {
            UserEntity user = new UserEntity("Melba", bcryptPasswordEncoder.encode("1234"), "melba@mindhub.com", "melba123");
            user.setRole(RoleType.ADMIN);
            userRepository.save(user);
            Task task = new Task("Tarea 1", "Tarea de prueba", TaskStatus.PENDING);
            user.addTask(task);
            taskRepository.save(task);
        };
    }

}
