package com.korit.mybatis_study.service;

import com.korit.mybatis_study.dto.AddBoardReqDto;
import com.korit.mybatis_study.dto.ApiRespDto;
import com.korit.mybatis_study.entity.Board;
import com.korit.mybatis_study.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public ApiRespDto<?> addBoard(AddBoardReqDto addBoardReqDto) {
        // title에 대해서 중복검사
        Optional<Board> findBoard = boardRepository.findBoardByTitle(addBoardReqDto.getTitle());
        if (findBoard.isPresent()) {
            return new ApiRespDto<>("failed", "제목은 중복될 수 없습니다.", null);
        }
        // 중복이 되지않으면 추가
        Optional<Board> board = boardRepository.addBoard(addBoardReqDto.toEntity());
        if (board.isEmpty()) {
            return new ApiRespDto<>("failed", "추가 실패", null);
        }
        return new ApiRespDto<>("success", "추가 성공", board.get());
    }

    public ApiRespDto<?> getBoardList() {
        return new ApiRespDto<>("success", "전체 조회 완료", boardRepository.getBoardList());
    }
}
