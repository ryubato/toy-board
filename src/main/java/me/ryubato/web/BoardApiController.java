package me.ryubato.web;

import lombok.RequiredArgsConstructor;
import me.ryubato.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/v1/boards")
    public Long save(@RequestBody BoardForm boardForm) {  // TODO @Valid 적용
        return boardService.save(boardForm);
    }

    @DeleteMapping("/api/v1/boards/{id}")
    public void delete(@PathVariable("id") Long id) {
        boardService.delete(id);
    }

    @PutMapping("/api/v1/boards/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody BoardForm boardForm) {
        boardService.update(id, boardForm);
    }

    @GetMapping("/api/v1/boards/{id}")
    public BoardDto findById(@PathVariable Long id) {
        return boardService.getBoard(id);
    }

    @GetMapping("/api/v1/boards")
    public List<BoardListDto> findAll() {
        return boardService.getBoards();
    }

    @GetMapping(value = "/api/v2/boards", params = {"page", "size"})
    public Page<BoardListDto> findAllWithPagingDefaultType(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return boardService.getBoardsWithPagingDefaultType(page, size);
    }

    @GetMapping(value = "/api/v3/boards", params = {"page", "size"})
    public CustomRestResponsePage<BoardListDto> findAllWithPagingTypeCustomRestResponsePage(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return boardService.getBoardsWithPagingCustomRestResponsePage(page, size);
    }
}
