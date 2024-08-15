package com.mindhub.todolist;

import com.mindhub.todolist.models.Task;
import com.mindhub.todolist.models.TaskStatus;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.TaskRepository;
import com.mindhub.todolist.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Utils {
    @Bean
    public CommandLineRunner commandLineRunner(UserRepository userRepository, TaskRepository taskRepository) {
        return args -> {
            UserEntity user = new UserEntity("Melba", "1234", "melba@mindhub.com");
            userRepository.save(user);
            Task task = new Task("Tarea 1", "Tarea de prueba", TaskStatus.PENDING);
            user.addTask(task);
            taskRepository.save(task);
        };
    }

}
