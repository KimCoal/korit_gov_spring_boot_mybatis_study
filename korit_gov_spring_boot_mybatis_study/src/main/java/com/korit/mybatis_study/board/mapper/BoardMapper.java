package com.korit.mybatis_study.board.mapper;

import com.korit.mybatis_study.board.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface BoardMapper {
    Optional<Board> findBoardByTitle(String title);
    int addBoard(Board board);
    List<Board> getBoardList();
    Optional<Board> getBoardByBoardId(Integer boardId);
    int editBoard(Board board);
    int deleteBoard(Integer boardId);
}
