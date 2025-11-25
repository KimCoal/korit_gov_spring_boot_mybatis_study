package com.korit.mybatis_study.todo.repository;

import com.korit.mybatis_study.board.entity.Board;
import com.korit.mybatis_study.todo.entity.Todo;
import com.korit.mybatis_study.todo.mapper.TodoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoRepository {

    @Autowired
    private TodoMapper todoMapper;

    public Optional<Todo> addTodo(Todo todo) {
        try {
            todoMapper.addTodo(todo);
        } catch (DuplicateKeyException e) {
            return Optional.empty();
        }
        return Optional.of(todo);
    }

    public Optional<Todo> findByTodoTitle(String title) {
        return todoMapper.findByTodoTitle(title);
    }

    public List<Todo> getAllTodo() {
        return todoMapper.getAllTodo();
    }

    public Optional<Todo> getTodoByTodoId(Integer todoId) {
        return todoMapper.getTodoByTodoId(todoId);
    }

    public int editTodo(Todo todo) {
        return todoMapper.editTodo(todo);
    }

    public int delTodo(Integer todoId) {
        return todoMapper.delTodo(todoId);
    }
}
