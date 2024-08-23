package com.mindhub.todolist.serviceTests;

import com.mindhub.todolist.dtos.NewTaskRecord;
import com.mindhub.todolist.models.Task;
import com.mindhub.todolist.models.TaskStatus;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.TaskRepository;
import com.mindhub.todolist.repositories.UserRepository;
import com.mindhub.todolist.services.TaskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@ActiveProfiles("test")
public class TaskServiceTests {
    @MockBean
    private TaskRepository taskRepository;
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private TaskService taskService;

//    @Test
//    public void createTaskTest() {
//        NewTaskRecord newTask = new NewTaskRecord("Finish Tests", "Finish all tests for sprint 4", TaskStatus
//        .PENDING);
//        UserEntity user = new UserEntity("pepito", "1234", "pepito@accenture.com", "pepito123");
//        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
//        userRepository.save(user);
//        when(userRepository.findByUsername(anyString())).thenReturn(Optional.of(user));
//        Task task = new Task(newTask.title(), newTask.description(), newTask.status());
//        when(taskRepository.save(any(Task.class))).thenReturn(task);
//        taskService.createTask(newTask, user.getUsername());
//    }

    @Test
    public void testCreateTask() {
        // Arrange
        NewTaskRecord taskRecord = new NewTaskRecord("Task Title", "Task Description", TaskStatus.PENDING);
        UserEntity user = new UserEntity("pepito", "1234", "pepito@accenture.com", "pepito123");
        Task expectedTask = new Task(taskRecord.title(), taskRecord.description(), taskRecord.status());

        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
        userRepository.save(user);
        // Mock the task repository
        when(taskRepository.save(any(Task.class))).thenReturn(expectedTask);

        // Mock the user repository to return the user
        when(userRepository.findByUsername(eq(user.getUsername()))).thenReturn(Optional.of(user));
        when(taskRepository.findById(eq(expectedTask.getId()))).thenReturn(Optional.of(expectedTask));
        taskService.createTask(taskRecord, user.getUsername());

        // Act
        Task actualTask = taskRepository.findById(expectedTask.getId()).orElse(null);

        // Assert
        verify(taskRepository, times(2)).save(any(Task.class));
        assertEquals(expectedTask, actualTask);
    }

}
