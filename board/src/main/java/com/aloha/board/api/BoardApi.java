package com.aloha.board.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aloha.board.doamin.Board;
import com.aloha.board.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController("BoardApi")
@RequestMapping("/api/board")
public class BoardApi {

  @Autowired private BoardService boardService;

  
  @GetMapping()
  public ResponseEntity<?> getAll() {
      try {
          List<Board> boardList = boardService.list();
          return new ResponseEntity<>(boardList, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @GetMapping("/{id}")
  public ResponseEntity<?> getOne(@PathVariable("id") String id) {
      try {
          Board board = boardService.select( Long.parseLong(id) );
          return new ResponseEntity<>(board, HttpStatus.OK);
      } 
      catch (NumberFormatException e) {
        Board board = boardService.selectById( id );
        return new ResponseEntity<>(board, HttpStatus.OK);
      }
      catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @PostMapping()
  public ResponseEntity<?> create(@RequestBody Board board) {
      try {
          boolean result = boardService.insert(board);
          if( result ) {
            log.info("Create Result: {}", board);
          } 
          else {
            log.info("Create Result: Fail to create board");
          }
          return new ResponseEntity<>(result, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @PutMapping()
  public ResponseEntity<?> update(@RequestBody Board board) {
      try {
        boolean result = false;
        if( board.getNo() != null ) {
          result = boardService.update(board);
        }
        else {
          result = boardService.updateById(board);
        }
        if( result ) {
          log.info("Update Result: {}", board);
        } 
        else {
          log.info("Update Result: Fail to update board");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
      } catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
  @DeleteMapping("/{id}")
  public ResponseEntity<?> destroy(@PathVariable("id") String id) {
      try {
          boolean result = boardService.delete( Long.parseLong(id) );
          if( result ) {
            log.info("Destroy Result: {}", id);
          } 
          else {
            log.info("Destroy Result: Fail to destroy board");
          }
          return new ResponseEntity<>(result, HttpStatus.OK);
      } 
      catch (NumberFormatException e) {
        boolean result = boardService.deleteById( id );
        if( result ) {
          log.info("Destroy Result: {}", id);
        } 
        else {
          log.info("Destroy Result: Fail to destroy board");
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
      }
      catch (Exception e) {
          return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
      }
  }
  
}
