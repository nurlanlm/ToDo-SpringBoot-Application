package com.nurlan.Todo.app.controller;

import com.nurlan.Todo.app.dto.TodoDTO;
import com.nurlan.Todo.app.entity.Todo;
import com.nurlan.Todo.app.exception.TodoNotFoundException;
import com.nurlan.Todo.app.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @GetMapping
    public ResponseEntity<List<TodoDTO>> getAllTodos(){
        return new ResponseEntity<>(todoService.getAllTodos(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoDTO> findTodoWithId(@PathVariable Long id){
        return new ResponseEntity<>(todoService.findTodoWithId(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<TodoDTO> createTodo(@RequestBody TodoDTO todoDTO){
        Todo todo = todoService.convertToEntity(todoDTO);
        todoService.createTodo(todo);
        return new ResponseEntity<>(todoService.convertToDTO(todo), HttpStatus.OK);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TodoDTO> editTodo(@RequestBody TodoDTO newTodoDTO, @PathVariable Long id){
        return new ResponseEntity<>(todoService.editTodo(newTodoDTO, id), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Todo deleted. ID: " + id, HttpStatus.OK);
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<String> deleteAll(){
        todoService.deleteAll();
        return new ResponseEntity<>("All todos deleted.", HttpStatus.OK);
    }


    @ExceptionHandler(value = TodoNotFoundException.class)
    public ResponseEntity<String> handleTodoNotFoundException(TodoNotFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
