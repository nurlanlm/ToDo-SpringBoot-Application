package com.nurlan.Todo.app.exception;

public class TodoNotFoundException extends RuntimeException{
    public TodoNotFoundException(String message){
        super(message);
    }
}
