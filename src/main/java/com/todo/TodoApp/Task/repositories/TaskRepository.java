package com.todo.TodoApp.Task.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.todo.TodoApp.Task.entities.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer>{
  Task findOneTaskById(Integer id);
} 
