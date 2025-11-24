package com.korit.mybatis_study.controller;

import com.korit.mybatis_study.dto.AddBoardReqDto;
import com.korit.mybatis_study.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardService boardService;

    @PostMapping("/add")
    public ResponseEntity<?> addBoard(@RequestBody AddBoardReqDto addBoardReqDto) {
        return ResponseEntity.ok(boardService.addBoard(addBoardReqDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getBoardList() {
        return ResponseEntity.ok(boardService.getBoardList());
    }
}
