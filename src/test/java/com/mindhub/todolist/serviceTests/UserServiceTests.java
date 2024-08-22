package com.mindhub.todolist.serviceTests;

import com.mindhub.todolist.models.UserEntity;
import com.mindhub.todolist.repositories.UserRepository;
import com.mindhub.todolist.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTests {

    @MockBean
    UserRepository userRepository;
    @Autowired
    UserService userService;

    @Test
    public void getUserByEmailTest() {
        UserEntity newUser = new UserEntity("Pepito", "123456", "pepito@accenture.com", "pepito123");
        userRepository.save(newUser);
        when(userRepository.findByEmail(anyString())).thenReturn(Optional.of(newUser));
        UserEntity found = userService.getUserByEmail("pepito@accenture.com");
        assertEquals(newUser.getEmail(), found.getEmail());
    }



}
