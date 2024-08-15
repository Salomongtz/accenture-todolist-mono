package com.mindhub.todolist.services;

import com.mindhub.todolist.dtos.NewTaskRecord;
import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.models.Task;

import java.util.Set;

public interface TaskService {
    Task getTaskById(Long id);

    void deleteTask(Long id);

    void createTask(NewTaskRecord task);

    Set<TaskDTO> getAllTasks();

    void updateTask(Long id, NewTaskRecord newTaskRecord);
}
