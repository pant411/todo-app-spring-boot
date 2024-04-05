package com.todo.TodoApp.Task.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.todo.TodoApp.Task.entities.Task;
import com.todo.TodoApp.Task.repositories.TaskRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Optional;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {
  @InjectMocks
  private TaskService taskService;

  @Mock
  private TaskRepository taskRepository;

  @Test
  public void testGetOneTask() {
    int id = 1;
    Task mockTask = new Task(id, "Task 1", false);
    // Mock the behavior of the repository to return the mock employee
    Mockito.when(taskRepository.findById(id)).thenReturn(Optional.of(mockTask));
    // Act
    Optional<Task> result = taskService.getOneTask(id);
    System.out.println("Test get one task!!!");
    System.out.println(result);
    // Assert
    assertNotNull(result);
    assertEquals(mockTask, result.get());
  }
}
