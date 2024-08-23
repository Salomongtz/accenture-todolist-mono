//package com.mindhub.todolist.controllerTests;
//
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.mindhub.todolist.dtos.NewTaskRecord;
//import com.mindhub.todolist.models.Task;
//import com.mindhub.todolist.services.TaskService;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.security.core.Authentication;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.ResultMatcher;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//
//import java.util.Map;
//
//import static org.hamcrest.Matchers.isA;
//import static org.springframework.test.web.client.match.MockRestRequestMatchers.jsonPath;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest
//public class TaskControllerTests extends MockMvcBuilders.WebAppContextSetup {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Autowired
//    private TaskService taskService; // Mock this for testing
//
//    @Before
//    public void setUp() {
//        this.mockMvc = setup(webApplicationContext).build();
//    }
//
//    @Test
//    public void createTaskTest() throws Exception {
//        String username = "testUser";
//        String title = "Test Task";
//        String description = "This is a test task.";
//        NewTaskRecord newTaskRecord = new NewTaskRecord(title, description, "DONE");
//
//        // Mock authentication with username
//        Authentication mockAuthentication = Mockito.mock(Authentication.class);
//        Mockito.when(mockAuthentication.getName()).thenReturn(username);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/task")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(asJsonString(newTaskRecord))
//                        .principal(mockAuthentication))
//                .andExpect(status().isCreated())
//                .andExpect((ResultMatcher) jsonPath("$").value("Task created successfully." + newTaskRecord));
//
//        // Verify task is created in the service (optional)
//        Mockito.verify(taskService).createTask(newTaskRecord, username);
//    }
//    @Test
//    public void getExistingTaskTest() throws Exception {
//        Long taskId = 1L; // Replace with actual existing task ID
//
//        // Mock user task (optional)
//        Task mockTask = new Task("Existing Task", "...", "TODO");
//        Mockito.when(taskService.getUserTaskById(taskId, "testUser")).thenReturn(mockTask);
//
//        mockMvc.perform(MockMvcRequestBuilders.get("/task/{id}", taskId)
//                        .accept(MediaType.APPLICATION_JSON)
//                        .principal(getMockAuthentication("testUser")))
//                .andExpect(status().isOk())
//                .andExpect((ResultMatcher) jsonPath("$", isA(Map.class))) // Assert JSON response structure
//                .andExpect(jsonPath("$.title").value(mockTask.getTitle())); // Check specific field
//    }
//
//    private Authentication getMockAuthentication(String username) {
//        Authentication mockAuthentication = Mockito.mock(Authentication.class);
//        Mockito.when(mockAuthentication.getName()).thenReturn(username);
//        return mockAuthentication;
//    }
//
//    private static String asJsonString(final Object obj) throws JsonProcessingException {
//        ObjectMapper mapper = new ObjectMapper();
//        return mapper.writeValueAsString(obj);
//    }
//}
