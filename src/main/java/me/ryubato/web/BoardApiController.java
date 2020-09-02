package me.ryubato.web;

import lombok.RequiredArgsConstructor;
import me.ryubato.domain.Board;
import me.ryubato.domain.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardRepository boardRepository;

    @PostMapping("/api/v1/boards")
    public Long save(@RequestBody BoardForm boardForm) {  // TODO @Valid 적용
        Board board = boardForm.toEntity();
        return boardRepository.save(board).getId();
    }

    @DeleteMapping("/api/v1/boards/{id}")
    public void delete(@RequestParam("id") Long id) {
        Board board = boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        boardRepository.delete(board);
    }

    @PutMapping("/api/v1/boards/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody BoardForm boardForm) {
        Board board = boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        board.updateBoard(boardForm.getTitle(), boardForm.getContent());
    }

    @GetMapping("/api/v1/boards/{id}")
    public BoardDto findById(@PathVariable Long id) {
        Board board = boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        return new BoardDto(board);
    }

    @GetMapping("/api/v1/boards")
    public List<BoardListDto> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardListDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/api/v2/boards", params = {"page", "size"})
    public Page<BoardListDto> findAllWithPagingDefaultType(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return boardRepository.findAll(PageRequest.of(page, size)).map(BoardListDto::new);
    }

    @GetMapping(value = "/api/v3/boards", params = {"page", "size"})
    public CustomRestResponsePage<BoardListDto> findAllWithPagingTypeCustomRestResponsePage(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        Page<BoardListDto> response = boardRepository.findAll(PageRequest.of(page, size)).map(BoardListDto::new);
        return new CustomRestResponsePage<BoardListDto>(response.getContent(), response.getNumber(), response.getSize(), response.getTotalElements());
    }
}
