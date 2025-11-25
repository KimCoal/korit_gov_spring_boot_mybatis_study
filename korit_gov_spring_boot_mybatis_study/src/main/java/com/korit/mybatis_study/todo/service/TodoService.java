package com.korit.mybatis_study.todo.service;

import com.korit.mybatis_study.board.dto.ApiRespDto;
import com.korit.mybatis_study.board.entity.Board;
import com.korit.mybatis_study.todo.dto.AddTodoReqDto;
import com.korit.mybatis_study.todo.dto.EditTodoReqDto;
import com.korit.mybatis_study.todo.entity.Todo;
import com.korit.mybatis_study.todo.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;

    public ApiRespDto<?> addTodo(AddTodoReqDto addTodoReqDto) {
        // 중복검사
        Optional<Todo> findTodo = todoRepository.findByTodoTitle(addTodoReqDto.getTitle());
        if (findTodo.isPresent()) {
            return new ApiRespDto<>("failed", "중복", null);
        }
        Optional<Todo> todo = todoRepository.addTodo(addTodoReqDto.toEntity());
        if (todo.isEmpty()) {
            return new ApiRespDto<>("failed", "추가 실패", null);
        }
        return new ApiRespDto<>("success", "성공", todo);
    }

    public ApiRespDto<?> getAllTodo() {
        return new ApiRespDto<>("success", "성공", todoRepository.getAllTodo());
    }

    public ApiRespDto<?> getTodoByTodoId(Integer todoId) {
        return new ApiRespDto<>("success", "성공", todoRepository.getTodoByTodoId(todoId));
    }

    public ApiRespDto<?> editTodo(EditTodoReqDto editTodoReqDto) {
        Optional<Todo> foundBoard = todoRepository.getTodoByTodoId(editTodoReqDto.getTodoId());
        if (foundBoard.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지 않습니다", null);
        }
        // 있으면 수정을 진행
        int result = todoRepository.editTodo(editTodoReqDto.toEntity());
        if (result != 1) {
            return new ApiRespDto<>("failed", "수정 실패", null);
        }
        return new ApiRespDto<>("success", "수정 성공", null);
    }

    public ApiRespDto<?> delTodo(Integer todoId) {
        Optional<Todo> foundTodo = todoRepository.getTodoByTodoId(todoId);
        if (foundTodo.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지 않습니다", null);
        }
        int result = todoRepository.delTodo(todoId);
        if (result != 1) {
            return new ApiRespDto<>("failed", "삭제 실패", null);
        }
        return new ApiRespDto<>("success", "삭제 성공", null);
    }
}
