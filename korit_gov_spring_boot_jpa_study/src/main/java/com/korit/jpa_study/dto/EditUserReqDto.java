package com.korit.jpa_study.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EditUserReqDto {
    private Integer userId;
    private String username;
    private String password;
    private String email;
}
