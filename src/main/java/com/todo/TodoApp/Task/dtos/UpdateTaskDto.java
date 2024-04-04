package com.todo.TodoApp.Task.dtos;

public class UpdateTaskDto {
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
  
}
