package com.mindhub.todolist.repositoryTests;

import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUsername() {
        // Arrange
        UserEntity user = new UserEntity("john", "password", "john@doe", "John Doe");
        userRepository.save(user);

        // Act
        UserEntity foundUser = userRepository.findByUsername("john").orElseThrow(() -> new RuntimeException("User not" +
                " found"));

        // Assert
        assertNotNull(foundUser);
        assertEquals("john", foundUser.getUsername());
    }
}
