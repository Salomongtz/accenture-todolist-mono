package com.mindhub.todolist.repositoryTests;

import com.mindhub.todolist.models.RoleType;
import com.mindhub.todolist.models.Task;
import com.mindhub.todolist.models.TaskStatus;
import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.TaskRepository;
import com.mindhub.todolist.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@ActiveProfiles("test")
public class TaskRepositoryTests {
    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void saveAndFindTaskTest() {
        String title = "Test Task";
        String description = "This is a test task.";
        TaskStatus status = TaskStatus.PENDING;

        Task newTask = new Task(title, description, status);
        taskRepository.save(newTask);

        Long savedTaskId = newTask.getId(); // Get the ID of the saved task

        Task retrievedTask = taskRepository.findById(savedTaskId).get(); // Use get() as we expect a result

        // Assert that the retrieved task matches the saved task
        assertEquals(title, retrievedTask.getTitle());
        assertEquals(description, retrievedTask.getDescription());
        assertEquals(status, retrievedTask.getStatus());
    }


//    @Test
//    public void testFindAll() {
//        UserEntity user = new UserEntity("Melba", "1234", "melba@mindhub.com",
//                "melba123");
//        user.setRole(RoleType.ADMIN);
//        userRepository.save(user);
//        Task task = new Task("Tarea 1", "Tarea de prueba", TaskStatus.PENDING);
//        user.addTask(task);
//        taskRepository.save(task);
//        assertThat(taskRepository.findAll().size(), greaterThanOrEqualTo(1));
//    }



}
