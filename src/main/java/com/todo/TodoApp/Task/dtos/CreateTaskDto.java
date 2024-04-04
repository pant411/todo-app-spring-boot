package com.todo.TodoApp.Task.dtos;

import com.todo.TodoApp.Task.entities.Task;

import jakarta.validation.constraints.NotEmpty;

public class CreateTaskDto {
  @NotEmpty(message = "Content is required")
  private String content;

  private boolean isCompleted;

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public boolean isCompleted() {
    return isCompleted;
  }

  public void setCompleted(boolean isCompleted) {
    this.isCompleted = isCompleted;
  }

  public Task toTask(String content, Boolean isCompleted) {
    return new Task(content, isCompleted);
  }
}
