package com.mindhub.todolist.controllers;

import com.mindhub.todolist.dtos.NewTaskRecord;
import com.mindhub.todolist.dtos.TaskDTO;
import com.mindhub.todolist.models.Task;
import com.mindhub.todolist.services.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    TaskService taskService;

    @PostMapping
    @Operation(summary = "Creates a new task.", tags = "Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "The task was created successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
            })
    })
    public ResponseEntity<?> createTask(@RequestBody NewTaskRecord task) {
        taskService.createTask(task);
        return ResponseEntity.status(201).body(task);
    }

    /**
     * Retrieves a task by its ID.
     *
     * @param id The ID of the task to retrieve.
     * @return A ResponseEntity containing the TaskDTO of the task if found, or a 404 Not Found response if not found.
     */
    @GetMapping("{id}")
    @Operation(summary = "Retrieves a task by its ID.", tags = "Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The TaskDTO of the task if found.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "The task was not found.")
    })
    public ResponseEntity<?> getTask(@RequestParam @Parameter(description = "The ID of the task to retrieve.",
            required = true) Long id) {
        Task task = taskService.getTaskById(id);
        if (task != null) {
            return ResponseEntity.ok(new TaskDTO(task));
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("all")
    @Operation(summary = "Retrieves all tasks.", tags = "Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The TaskDTOs of all tasks.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
            })
    })
    public Set<TaskDTO> getAllTasks() {
        return taskService.getAllTasks();
    }

    @PutMapping("{id}")
    @Operation(summary = "Updates a task by its ID.", tags = "Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The task was updated successfully.", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
            }),
            @ApiResponse(responseCode = "404", description = "The task was not found.")
    })
    public ResponseEntity<?> updateTask(@RequestParam @Parameter(description = "The ID of the task to update.",
            required = true) Long id, @RequestBody NewTaskRecord newTaskRecord) {
        if (taskService.getTaskById(id) != null) {
            taskService.updateTask(id, newTaskRecord);
            return ResponseEntity.ok(new TaskDTO(taskService.getTaskById(id)));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("{id}")
    @Operation(summary = "Deletes a task by its ID.", tags = "Task")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "The task was deleted successfully."),
            @ApiResponse(responseCode = "404", description = "The task was not found.")
    })
    public ResponseEntity<?> deleteTask(@RequestParam @Parameter(description = "The ID of the task to delete.",
            required = true) Long id) {
        if (taskService.getTaskById(id) != null) {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
