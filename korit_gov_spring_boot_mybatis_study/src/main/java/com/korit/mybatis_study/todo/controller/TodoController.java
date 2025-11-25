package com.korit.mybatis_study.todo.controller;


import com.korit.mybatis_study.todo.dto.AddTodoReqDto;
import com.korit.mybatis_study.todo.dto.EditTodoReqDto;
import com.korit.mybatis_study.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping("/add")
    public ResponseEntity<?> addTodo(@RequestBody AddTodoReqDto addTodoReqDto) {
        return ResponseEntity.ok(todoService.addTodo(addTodoReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllTodo() {
        return ResponseEntity.ok(todoService.getAllTodo());
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<?> getTodoByTodoId(@PathVariable Integer todoId) {
        return ResponseEntity.ok(todoService.getTodoByTodoId(todoId));
    }

    @PostMapping("/update")
    public ResponseEntity<?> editTodo(@RequestBody EditTodoReqDto editTodoReqDto) {
        return ResponseEntity.ok(todoService.editTodo(editTodoReqDto));
    }

    @PostMapping("/delete")
    public ResponseEntity<?> delTodo(@RequestParam Integer todoId){
        return ResponseEntity.ok(todoService.delTodo(todoId));
    }
}
