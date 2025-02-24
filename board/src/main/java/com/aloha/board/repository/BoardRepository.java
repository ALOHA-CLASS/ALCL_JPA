package com.aloha.board.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aloha.board.doamin.Board;


public interface BoardRepository extends CrudRepository<Board, Long> {

  Optional<Board> findByNo(Long no);
  Optional<Board> findById(String id);

}
