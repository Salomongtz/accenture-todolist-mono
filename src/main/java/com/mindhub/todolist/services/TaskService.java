package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.NewTaskRecord;
import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.models.Task;

import java.util.Set;

public interface TaskService {
    Task getUserTaskById(Long id, String name);

    void deleteTask(Long id);

    void createTask(NewTaskRecord task, String name);

    Set<TaskDTO> getAllUserTasks(String name);

    void updateTask(Long id, NewTaskRecord newTaskRecord);
}
