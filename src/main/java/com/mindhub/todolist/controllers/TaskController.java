//package com.mindhub.todolist.controllers;
//
//import com.mindhub.todolist.dtos.NewTaskRecord;
//import com.mindhub.todolist.dtos.TaskDTO;
//import com.mindhub.todolist.models.Task;
//import com.mindhub.todolist.services.TaskService;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.Parameter;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Set;
//
//@RestController
//@RequestMapping("/task")
//public class TaskController {
//    @Autowired
//    private TaskService taskService;
//
//    @PostMapping
//    @Operation(summary = "Creates a new task for current user.", tags = "Task")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "The task was created successfully.", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
//            })
//    })
//    public ResponseEntity<?> createTask(@RequestBody NewTaskRecord task, Authentication authentication) {
//        if (task == null) {
//            return ResponseEntity.badRequest().body("The task cannot be null.");
//        }
//        if (task.title().isEmpty() || task.title().isBlank()) {
//            return ResponseEntity.badRequest().body("The title cannot be empty.");
//        }
//        if (task.description().isEmpty() || task.description().isBlank()) {
//            return ResponseEntity.badRequest().body("The description cannot be empty.");
//        }
//        if (task.status() == null) {
//            return ResponseEntity.badRequest().body("The status cannot be null.");
//        }
//        taskService.createTask(task, authentication.getName());
//        return ResponseEntity.status(201).body("Task created successfully." + task);
//    }
//
//    @GetMapping("{id}")
//    @Operation(summary = "Retrieves a task from current user by its ID.", tags = "Task")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "The TaskDTO of the task if found.", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
//            }),
//            @ApiResponse(responseCode = "404", description = "The task was not found.")
//    })
//    public ResponseEntity<?> getTask(@RequestParam @Parameter(description = "The ID of the task to retrieve.",
//            required = true) Long id, Authentication authentication) {
//        if (id == null || id == 0 || id < 0) {
//            return ResponseEntity.badRequest().body("The ID cannot be null, negative or 0.");
//        }
//        Task task = taskService.getUserTaskById(id, authentication.getName());
//        if (task != null) {
//            return ResponseEntity.ok(new TaskDTO(task));
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @GetMapping("all")
//    @Operation(summary = "Retrieves all tasks from current user.", tags = "Task")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "The TaskDTOs from the user.", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
//            })
//    })
//    public ResponseEntity<?> getAllTasks(Authentication authentication) {
//        return ResponseEntity.ok().body(taskService.getAllUserTasks(authentication.getName()));
//    }
//
//    @PutMapping("{id}")
//    @Operation(summary = "Updates a task by its ID.", tags = "Task")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "The task was updated successfully.", content = {
//                    @Content(mediaType = "application/json", schema = @Schema(implementation = TaskDTO.class))
//            }),
//            @ApiResponse(responseCode = "404", description = "The task was not found.")
//    })
//    public ResponseEntity<?> updateTask(@RequestParam @Parameter(description = "The ID of the task to update.",
//            required = true) Long id, @RequestBody NewTaskRecord newTaskRecord, Authentication authentication) {
//        if (newTaskRecord == null) {
//            return ResponseEntity.badRequest().body("The task cannot be null.");
//        }
//        if (newTaskRecord.title().isEmpty() || newTaskRecord.title().isBlank()) {
//            return ResponseEntity.badRequest().body("The title cannot be empty.");
//        }
//        if (newTaskRecord.description().isEmpty() || newTaskRecord.description().isBlank()) {
//            return ResponseEntity.badRequest().body("The description cannot be empty.");
//        }
//        if (newTaskRecord.status() == null) {
//            return ResponseEntity.badRequest().body("The status cannot be null.");
//        }
//        if (taskService.getUserTaskById(id, authentication.getName()) != null) {
//            taskService.updateTask(id, newTaskRecord);
//            return ResponseEntity.ok(new TaskDTO(taskService.getUserTaskById(id, authentication.getName())));
//        }
//        return ResponseEntity.notFound().build();
//    }
//
//    @DeleteMapping("{id}")
//    @Operation(summary = "Deletes a task by its ID.", tags = "Task")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "204", description = "The task was deleted successfully."),
//            @ApiResponse(responseCode = "404", description = "The task was not found.")
//    })
//    public ResponseEntity<?> deleteTask(@RequestParam @Parameter(description = "The ID of the task to delete.",
//            required = true) Long id, Authentication authentication) {
//        if (id == null || id == 0 || id < 0) {
//            return ResponseEntity.badRequest().body("The ID cannot be null, negative or 0.");
//        }
//        if (taskService.getUserTaskById(id, authentication.getName()) != null) {
//            taskService.deleteTask(id);
//            return ResponseEntity.noContent().build();
//        }
//        return ResponseEntity.notFound().build();
//    }
//}
