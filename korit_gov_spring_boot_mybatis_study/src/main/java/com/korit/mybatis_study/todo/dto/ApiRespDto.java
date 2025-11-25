package com.korit.mybatis_study.todo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ApiRespDto<T> {
    private String status;
    private String message;
    private T data;
}
