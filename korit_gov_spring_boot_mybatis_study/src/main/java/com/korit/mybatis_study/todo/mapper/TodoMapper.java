package com.korit.mybatis_study.todo.mapper;

import com.korit.mybatis_study.todo.entity.Todo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface TodoMapper {
    Optional<Todo> findByTodoTitle(String title);
    int addTodo(Todo todo);
    List<Todo> getAllTodo();
    Optional<Todo> getTodoByTodoId(Integer todoId);
    int editTodo(Todo todo);
    int delTodo(Integer todoId);
}
