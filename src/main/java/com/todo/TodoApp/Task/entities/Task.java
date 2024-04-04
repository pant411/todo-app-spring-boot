package com.todo.TodoApp.Task.entities;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Table(name = "tasks")
@Entity
public class Task {
  @Id
  @SequenceGenerator(name = "task_sequence", sequenceName = "task_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "task_sequence")
  @Column(nullable = false)
  private Integer id;

  @Column(nullable = false, name = "content")
  private String content;

  @Column(nullable = false, name = "is_completed", columnDefinition = "boolean default false")
  private boolean isCompleted;

  @CreationTimestamp
  @Column(updatable = false, name = "created_at")
  private Date createdAt;

  @UpdateTimestamp
  @Column(name = "updated_at")
  private Date updatedAt;

  public Task() {}

  public Task(String content, boolean isCompleted) {
    this.content = content;
    this.isCompleted = isCompleted;
  }

  public Integer getId() {
    return id;
  }

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

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Date getUpdatedAt() {
    return updatedAt;
  }

  public void setUpdatedAt(Date updatedAt) {
    this.updatedAt = updatedAt;
  }

  @Override
  public String toString() {
    return "Task [id=" + id + ", content=" + content + ", isCompleted=" + isCompleted + ", createdAt=" + createdAt
        + ", updatedAt=" + updatedAt + "]";
  }
}
