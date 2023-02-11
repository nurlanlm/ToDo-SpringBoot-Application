package com.nurlan.Todo.app.repository;

import com.nurlan.Todo.app.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
