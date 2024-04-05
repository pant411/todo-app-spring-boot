package com.todo.TodoApp.Task.controllers;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.todo.TodoApp.Task.dtos.CreateTaskDto;
import com.todo.TodoApp.Task.dtos.UpdateTaskDto;
import com.todo.TodoApp.Task.entities.Task;
import com.todo.TodoApp.Task.services.TaskService;
import com.todo.TodoApp.exceptions.caseExceptions.NotFoundException;
import com.todo.TodoApp.shared.response.models.ResponseWithDataModel;
import com.todo.TodoApp.shared.response.models.base.BaseResponseModel;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "/api/task")
public class TaskController {
  @Autowired
  private TaskService taskService;

  @PostMapping
  public ResponseEntity<ResponseWithDataModel<Task>> create(@Valid @RequestBody CreateTaskDto createTaskDto) {
    Task newTask = this.taskService
        .create(createTaskDto.toTask(createTaskDto.getContent(), createTaskDto.isCompleted()));
    ResponseWithDataModel<Task> taskResponse = new ResponseWithDataModel<Task>(HttpStatus.CREATED.value(),
        "Create task successfully", newTask);
    return new ResponseEntity<ResponseWithDataModel<Task>>(taskResponse, HttpStatus.CREATED);
  }

  @GetMapping
  public ResponseEntity<ResponseWithDataModel<List<Task>>> getAllTask() {
    List<Task> tasks = this.taskService.getAllTask();
    Boolean isEmpty = tasks.size() < 1;
    HttpStatusCode httpStatusCode = isEmpty ? HttpStatus.NOT_FOUND : HttpStatus.OK;
    String message = isEmpty ? "Not Found Tasks" : "Found tasks";
    ResponseWithDataModel<List<Task>> taskResponse = new ResponseWithDataModel<List<Task>>(httpStatusCode.value(),
        message, tasks);
    return new ResponseEntity<ResponseWithDataModel<List<Task>>>(taskResponse, httpStatusCode);
  }

  @GetMapping(path = "{id}")
  public ResponseEntity<ResponseWithDataModel<Task>> getOneTask(@PathVariable("id") int id) throws NotFoundException {
    Task tasks = this.taskService.getOneTask(id);
    ResponseWithDataModel<Task> taskResponse = new ResponseWithDataModel<Task>(
        HttpStatus.OK.value(),
        "Found Task", tasks);
    return new ResponseEntity<ResponseWithDataModel<Task>>(taskResponse, HttpStatus.OK);
  }

  @PatchMapping(path = "{id}")
  public ResponseEntity<ResponseWithDataModel<Task>> update(@PathVariable("id") int id,
      @Valid @RequestBody UpdateTaskDto updateTaskDto) throws BadRequestException {
    Task updateTask = this.taskService.update(id, updateTaskDto);
    ResponseWithDataModel<Task> taskResponse = new ResponseWithDataModel<Task>(
        HttpStatus.CREATED.value(),
        "Update task successfully", updateTask);
    return new ResponseEntity<ResponseWithDataModel<Task>>(taskResponse, HttpStatus.CREATED);
  }

  @DeleteMapping(path = "{id}")
  public ResponseEntity<BaseResponseModel> deleteTask(@PathVariable("id") int id) throws BadRequestException {
    this.taskService.deleteTask(id);
    BaseResponseModel resposne = new BaseResponseModel(HttpStatus.OK.value(), "Delete task successfully");
    return new ResponseEntity<BaseResponseModel>(resposne, HttpStatus.OK);
  }
}
