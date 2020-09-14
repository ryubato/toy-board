package me.ryubato.service;

import lombok.RequiredArgsConstructor;
import me.ryubato.domain.Board;
import me.ryubato.domain.BoardRepository;
import me.ryubato.web.BoardDto;
import me.ryubato.web.BoardForm;
import me.ryubato.web.BoardListDto;
import me.ryubato.web.CustomRestResponsePage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public Long save(BoardForm boardForm) {
        return boardRepository.save(boardForm.toEntity()).getId();
    }

    public void delete(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        boardRepository.delete(board);
    }

    public void update(Long boardId, BoardForm boardForm) {
        Board board = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        board.updateBoard(boardForm.getTitle(), boardForm.getContent());
    }

    public BoardDto getBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);
        return new BoardDto(board);
    }

    public List<BoardListDto> getBoards() {
        return boardRepository.findAll().stream().map(BoardListDto::new).collect(Collectors.toList());
    }

    public Page<BoardListDto> getBoardsWithPagingDefaultType(int page, int size) {
        return boardRepository.findAll(PageRequest.of(page, size)).map(BoardListDto::new);
    }

    public CustomRestResponsePage<BoardListDto> getBoardsWithPagingCustomRestResponsePage(int page, int size) {
        Page<BoardListDto> response = boardRepository.findAll(PageRequest.of(page, size)).map(BoardListDto::new);
        return new CustomRestResponsePage<>(response.getContent(), response.getNumber(), response.getSize(), response.getTotalElements());
    }

}
