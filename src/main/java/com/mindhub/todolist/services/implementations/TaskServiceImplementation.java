package com.mindhub.todolist.services.implementations;

import com.mindhub.todolist.dtos.NewTaskRecord;
import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.models.Task;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.TaskRepository;
import com.mindhub.todolist.repositories.UserRepository;
import com.mindhub.todolist.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class TaskServiceImplementation implements TaskService {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public void createTask(NewTaskRecord newTaskRecord, String username) {
        Task task = new Task(newTaskRecord.title(), newTaskRecord.description(), newTaskRecord.status());
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not " +
                "found"));
        user.addTask(task);
        try {
            taskRepository.save(task);
        } catch (Exception e) {
            throw new RuntimeException("Error creating task", e);
        }
    }

    @Override
    public Task getUserTaskById(Long id, String name) {
        UserEntity user = userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getTasks().stream().noneMatch(task -> task.getId().equals(id))) {
            throw new IllegalArgumentException("The task doesn't exist");
        }
        return user.getTasks().stream().filter(task -> task.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public Set<TaskDTO> getAllUserTasks(String name) {
        UserEntity user = userRepository.findByUsername(name).orElseThrow(() -> new RuntimeException("User not found"));
        if (user.getTasks().isEmpty()) {
            throw new IllegalArgumentException("The user doesn't have tasks");
        }
        return user.getTasks().stream().map(TaskDTO::new).collect(Collectors.toSet());
    }

    @Override
    public void updateTask(Long id, NewTaskRecord newTaskRecord) {
        Task task = taskRepository.findById(id).orElse(null);
        assert task != null;
        task.setTitle(newTaskRecord.title());
        task.setDescription(newTaskRecord.description());
        task.setStatus(newTaskRecord.status());
        taskRepository.save(task);
    }

    @Override
    public void deleteTask(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("The task doesn't exist");
        }
    }
}
