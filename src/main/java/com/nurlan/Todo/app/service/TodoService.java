package com.nurlan.Todo.app.service;

import com.nurlan.Todo.app.dto.TodoDTO;
import com.nurlan.Todo.app.entity.Todo;

import java.util.List;

public interface TodoService {

    List<TodoDTO> getAllTodos();
    void createTodo(Todo todo);
    TodoDTO findTodoWithId(Long id);
    TodoDTO editTodo(TodoDTO newTodoDTO, Long id);
    void deleteTodo(Long id);
    void deleteAll();
    TodoDTO convertToDTO(Todo todo);
    Todo convertToEntity(TodoDTO todoDTO);

}
