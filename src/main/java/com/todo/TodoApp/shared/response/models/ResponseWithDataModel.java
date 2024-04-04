package com.todo.TodoApp.shared.response.models;

import com.todo.TodoApp.shared.response.models.base.BaseResponseModel;

public class ResponseWithDataModel<T> extends BaseResponseModel {
  private T data;

  public ResponseWithDataModel(int statusCode, String message, T data) {
    super(statusCode, message);
    this.data = data;
  }

  public T getData() {
    return data;
  }

  public void setData(T data) {
    this.data = data;
  }

}
