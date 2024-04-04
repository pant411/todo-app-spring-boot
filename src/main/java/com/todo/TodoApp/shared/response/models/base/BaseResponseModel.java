package com.todo.TodoApp.shared.response.models.base;


public class BaseResponseModel {
  private int statusCode;
  private String message;

  public BaseResponseModel(int statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }
  
  public void setMessage(String message) {
    this.message = message;
  }
}
