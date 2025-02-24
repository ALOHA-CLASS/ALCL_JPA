package com.aloha.board;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.aloha.board.doamin.Board;
import com.aloha.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class BoardTest {

  @Autowired private BoardService boardService;

  @Test
  public void insert() {

    Board board = Board.builder()       
                        .id(UUID.randomUUID().toString())
                        .title("제목")
                        .writer("글쓴이")
                        .content("내용")
                        .build();
    boolean result = boardService.insert(board);
    
    if( result ) {
      log.info("게시글 등록 성공");
    } else {
      log.info("게시글 등록 실패");
    }

  }
  
}
