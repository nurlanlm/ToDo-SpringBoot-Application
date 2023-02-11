package com.nurlan.Todo.app.service;

import com.nurlan.Todo.app.dto.TodoDTO;
import com.nurlan.Todo.app.entity.Todo;
import com.nurlan.Todo.app.exception.TodoNotFoundException;
import com.nurlan.Todo.app.repository.TodoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    @Override
    public List<TodoDTO> getAllTodos() {
        List<Todo> todoList = todoRepository.findAll();
        return todoList.stream().map(this::convertToDTO).collect(Collectors.toList());

//        return todoDTOList = todoList.stream().map(todo -> {
//            TodoDTO todoDTO = new TodoDTO();
//            todoDTO.setId(todo.getId());
//            todoDTO.setName(todo.getName());
//            todoDTO.setDescription(todo.getDescription());
//            todoDTO.setCompleted(todo.isCompleted());
//            todoDTO.setDateAndTime(todo.getDateAndTime());
//            return todoDTO;
//        }).collect(Collectors.toList());

    }

    @Override
    public void createTodo(Todo todo) {
        todoRepository.save(todo);
    }

    @Override
    public TodoDTO findTodoWithId(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() ->
                new TodoNotFoundException("Todo not found. ID: " + id));

        return convertToDTO(todo);
    }

    @Override
    public TodoDTO editTodo(TodoDTO newTodoDTO, Long id) {

        Todo oldTodo = convertToEntity(findTodoWithId(id));

        oldTodo.setId(oldTodo.getId());
        oldTodo.setName(newTodoDTO.getName());
        oldTodo.setDescription(newTodoDTO.getDescription());
        oldTodo.setCompleted(newTodoDTO.isCompleted());
        oldTodo.setDateAndTime(newTodoDTO.getDateAndTime());
        todoRepository.save(oldTodo);

        return convertToDTO(oldTodo);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.delete(convertToEntity(findTodoWithId(id)));
    }

    @Override
    public TodoDTO convertToDTO(Todo todo){
        TodoDTO todoDTO = new TodoDTO();

        todoDTO.setId(todo.getId());
        todoDTO.setName(todo.getName());
        todoDTO.setDescription(todo.getDescription());
        todoDTO.setCompleted(todo.isCompleted());
        todoDTO.setDateAndTime(todo.getDateAndTime());
        return todoDTO;
    }

    @Override
    public Todo convertToEntity(TodoDTO todoDTO){
        Todo todo = new Todo();

        todo.setId(todoDTO.getId());
        todo.setName(todoDTO.getName());
        todo.setDescription(todoDTO.getDescription());
        todo.setCompleted(todoDTO.isCompleted());
        todo.setDateAndTime(todoDTO.getDateAndTime());
        return todo;
    }



    @Override
    public void deleteAll() {
        todoRepository.deleteAll();
    }


}
