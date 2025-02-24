package com.aloha.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy.Content;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aloha.board.doamin.Board;
import com.aloha.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

/**
 * 게시글
 * GET - /board : 게시글 목록
 * GET - /board/{id} : 게시글 상세
 * GET - /board/create : 게시글 등록
 * POST - /board : 게시글 등록
 * GET - /board/edit : 게시글 수정
 * PUT - /board : 게시글 수정
 * DELETE - /board/{id} : 게시글 삭제
 * 
 */
@Slf4j
@Controller
@RequestMapping("/board")
public class BoardController {


  @Autowired BoardService boardService;

  @GetMapping
  public String getBoardList(
    Model model
  ) {
    // 게시글 목록 조회 로직
    List<Board> list = boardService.list();
    model.addAttribute("list", list);
    return "board/list";
  }

  @GetMapping("/{id}")
  public String getBoardDetail(
    @PathVariable("id") String id,
    Model model
  ) {
    // 게시글 상세 조회 로직
    Board board = boardService.selectById(id);
    model.addAttribute("board", board);
    return "board/detail";
  }

  @GetMapping("/create")
  public String createBoardForm() {
    // 게시글 등록 폼 조회 로직
    return "board/create";
  }

  @PostMapping
  public String createBoard(Board board) {
    // 게시글 등록 로직
    boolean result = boardService.insert(board);
    if (!result) {
      log.error("게시글 등록 실패");
    }
    return "redirect:/board";
  }

  @GetMapping("/edit/{id}")
  public String editBoardForm(
    @PathVariable("id") String id,
    Model model
  ) {
    // 게시글 수정 폼 조회 로직
    Board board = boardService.selectById(id);
    model.addAttribute("board", board);
    return "board/edit";
  }

  @PutMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<?> editBoard(@RequestBody Board board) {
    // 게시글 수정 로직
    boolean result = boardService.updateById(board);
    if (!result) {
      log.error("게시글 수정 실패");
    }
    return ResponseEntity.ok(result);
  }

  @PutMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
  public ResponseEntity<?> editBoardForm(Board board) {
    // 게시글 수정 로직
    boolean result = boardService.updateById(board);
    if (!result) {
      log.error("게시글 수정 실패");
    }
    return ResponseEntity.ok(result);
  }


  @DeleteMapping("/{id}")
  public ResponseEntity<?> deleteBoard(@PathVariable("id") String id) {
    // 게시글 삭제 로직
    boolean result = boardService.deleteById(id);
    if (!result) {
      log.error("게시글 삭제 실패");
    }
    return ResponseEntity.ok(result);
  }


  
}
