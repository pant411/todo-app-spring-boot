package com.todo.TodoApp.exceptions;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.todo.TodoApp.exceptions.caseExceptions.ForbiddenException;

import com.todo.TodoApp.shared.response.models.ResponseWithErrorFieldValidate;
import com.todo.TodoApp.shared.response.models.base.BaseResponseModel;

@ControllerAdvice
public class GlobalException {
  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<BaseResponseModel> notFoundException(NotFoundException ex, WebRequest request) {
    BaseResponseModel message = new BaseResponseModel(
        HttpStatus.NOT_FOUND.value(),
        ex.getMessage());

    return new ResponseEntity<BaseResponseModel>(message, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ResponseWithErrorFieldValidate> validateException(MethodArgumentNotValidException ex,
      WebRequest request) {
    List<String> fieldErrors = new ArrayList<>();
    ex.getAllErrors().forEach(err -> fieldErrors.add(err.getDefaultMessage()));
    ResponseWithErrorFieldValidate message = new ResponseWithErrorFieldValidate(
        HttpStatus.BAD_REQUEST.value(),
        "Validation fail",
        fieldErrors);

    return new ResponseEntity<ResponseWithErrorFieldValidate>(message, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(ForbiddenException.class)
  public ResponseEntity<BaseResponseModel> forbiddenException(ForbiddenException ex, WebRequest request) {
    BaseResponseModel message = new BaseResponseModel(
        HttpStatus.FORBIDDEN.value(),
        ex.getMessage());

    return new ResponseEntity<BaseResponseModel>(message, HttpStatus.FORBIDDEN);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<BaseResponseModel> badRequestException(BadRequestException ex, WebRequest request) {
    BaseResponseModel message = new BaseResponseModel(
        HttpStatus.BAD_REQUEST.value(),
        ex.getMessage());

    return new ResponseEntity<BaseResponseModel>(message, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<BaseResponseModel> globalExceptionHandler(Exception ex, WebRequest request) {
    BaseResponseModel message = new BaseResponseModel(
        HttpStatus.INTERNAL_SERVER_ERROR.value(),
        ex.getMessage());

    return new ResponseEntity<BaseResponseModel>(message, HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
