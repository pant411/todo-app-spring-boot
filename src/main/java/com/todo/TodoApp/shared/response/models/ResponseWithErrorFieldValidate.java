package com.todo.TodoApp.shared.response.models;

import java.util.List;

import com.todo.TodoApp.shared.response.models.base.BaseResponseModel;

public class ResponseWithErrorFieldValidate extends BaseResponseModel {
  private List<String> fieldErrors;

  public ResponseWithErrorFieldValidate(int statusCode, String message, List<String> fieldErrors) {
    super(statusCode, message);
    this.fieldErrors = fieldErrors;
  }

  public List<String> getFieldErrors() {
    return fieldErrors;
  }

  public void setFieldErrors(List<String> fieldErrors) {
    this.fieldErrors = fieldErrors;
  }

}
