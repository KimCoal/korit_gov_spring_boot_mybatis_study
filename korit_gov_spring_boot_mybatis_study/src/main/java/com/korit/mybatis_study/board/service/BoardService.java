package com.korit.mybatis_study.board.service;

import com.korit.mybatis_study.board.dto.AddBoardReqDto;
import com.korit.mybatis_study.board.dto.ApiRespDto;
import com.korit.mybatis_study.board.dto.EditBoardReqDto;
import com.korit.mybatis_study.board.entity.Board;
import com.korit.mybatis_study.board.repository.BoardRepository;
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

    public ApiRespDto<?> getBoardByBoardId(Integer boardId) {
        return new ApiRespDto<>("success", "조회 완료", boardRepository.getBoardByBoardId(boardId));
    }

    public ApiRespDto<?> editBoard(EditBoardReqDto editBoardReqDto) {
        // 해당 board가 존재하는지 확인
        Optional<Board> foundBoard = boardRepository.getBoardByBoardId(editBoardReqDto.getBoardId());
        if (foundBoard.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지 않습니다", null);
        }
        // 있으면 수정을 진행
        int result = boardRepository.editBoard(editBoardReqDto.toEntity());
        if (result != 1) {
            return new ApiRespDto<>("failed", "수정 실패", null);
        }
        return new ApiRespDto<>("success", "수정 성공", null);

    }

    public ApiRespDto<?> deleteBoard(Integer boardId) {
        Optional<Board> foundBoard = boardRepository.getBoardByBoardId(boardId);
        if (foundBoard.isEmpty()) {
            return new ApiRespDto<>("failed", "해당 게시물이 존재하지 않습니다", null);
        }
        int result = boardRepository.deleteBoard(boardId);
        if (result != 1) {
            return new ApiRespDto<>("failed", "게시물을 삭제하는데 문제가 발생", null);
        }
        return new ApiRespDto<>("seccess", "삭제 성공", null);
    }
}
