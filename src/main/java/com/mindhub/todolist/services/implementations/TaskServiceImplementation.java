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
    TaskRepository taskRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public void createTask(NewTaskRecord newTaskRecord) {
        Task task = new Task(newTaskRecord.title(), newTaskRecord.description(), newTaskRecord.status());
        UserEntity user = userRepository.findById(1L).orElse(null);
        assert user != null;
        user.addTask(task);
        taskRepository.save(task);
    }

    @Override
    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElse(null);
    }

    @Override
    public Set<TaskDTO> getAllTasks() {
        return new HashSet<>(taskRepository.findAll()).stream().map(TaskDTO::new).collect(Collectors.toSet());
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
