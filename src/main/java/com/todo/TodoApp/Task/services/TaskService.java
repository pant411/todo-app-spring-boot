package com.todo.TodoApp.Task.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.todo.TodoApp.Task.dtos.UpdateTaskDto;
import com.todo.TodoApp.Task.entities.Task;
import com.todo.TodoApp.Task.repositories.TaskRepository;
import com.todo.TodoApp.exceptions.caseExceptions.NotFoundException;

import jakarta.transaction.Transactional;

@Service
public class TaskService {
  @Autowired
  private TaskRepository taskRepository;

  public Task create(Task task) {
    return this.taskRepository.save(task);
  }

  public List<Task> getAllTask() {
    return this.taskRepository.findAll();
  }

  public Optional<Task> getOneTask(Integer id) {
    Optional<Task> task = this.taskRepository.findById(id);
    if (task.isEmpty()) {
      throw new NotFoundException("Task not exist");
    }
    return task;
  }

  @Transactional
  public Task update(Integer id, UpdateTaskDto updateTaskDto) throws BadRequestException {
    Task task = this.taskRepository.findById(id)
        .orElseThrow(() -> new BadRequestException(
            "Task with id " + id + " does not exist"));
    String newContent = updateTaskDto.getContent();
    Boolean newIsCompleted = updateTaskDto.isCompleted();
    if (newContent != null && !newContent.isEmpty() && !(Objects.equals(task.getContent(), newContent))) {
      task.setContent(newContent);
    }
    if (task.isCompleted() != newIsCompleted) {
      task.setCompleted(newIsCompleted);
    }
    return task;
  }

  public void deleteTask(Integer id) throws BadRequestException {
    boolean exits = this.taskRepository.existsById(id);
    if (!exits) {
        throw new BadRequestException("Task with id " + id + " does not exist");
    }
    this.taskRepository.deleteById(id);
  }
}
