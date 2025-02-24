package com.aloha.board.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aloha.board.doamin.Board;
import com.aloha.board.repository.BoardRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired BoardRepository boardRepository;

    @Override
    public List<Board> list() {
        Iterable<Board> boards = boardRepository.findAll();
        return (List<Board>) boards;
    }

    @Override
    public Board select(Long no) {
        Board board = boardRepository.findByNo(no).get();
        return board;
    }   

    @Override
    public Board selectById(String id) {
        Board board = boardRepository.findById(id).get();
        return board;
    }

    @Override
    @Transactional
    public boolean insert(Board entity) {
        Board board = boardRepository.save(entity);
        return board != null;
    }

    @Override
    public boolean update(Board entity) {
        Optional<Board> boardOptional = boardRepository.findByNo(entity.getNo());
        Board updateBoard = null;
        if( boardOptional.isPresent() ) {
            Board board = boardOptional.get();
            board.setTitle(entity.getTitle());
            board.setContent(entity.getContent());
            board.setWriter(entity.getWriter());
            board.setUpdatedAt(new Date());
            updateBoard = boardRepository.save(board);
        }
        return updateBoard != null;
    }

    @Override
    public boolean updateById(Board entity) {
        Optional<Board> boardOptional = boardRepository.findById(entity.getId());
        Board updateBoard = null;
        if( boardOptional.isPresent() ) {
            Board board = boardOptional.get();
            board.setTitle(entity.getTitle());
            board.setContent(entity.getContent());
            board.setWriter(entity.getWriter());
            board.setUpdatedAt(new Date());
            updateBoard = boardRepository.save(board);
        }
        return updateBoard != null;
    }

    @Override
    public boolean delete(Long no) {
        if (boardRepository.existsById(no)) {
            boardRepository.deleteById(no);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteById(String id) {
        Board board = boardRepository.findById(id).get();
        if ( board != null ) {
            boardRepository.delete(board);
            return true;
        } else {
            return false;
        }
    }

}
