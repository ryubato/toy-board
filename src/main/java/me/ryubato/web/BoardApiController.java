package me.ryubato.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.RequiredArgsConstructor;
import me.ryubato.domain.Board;
import me.ryubato.domain.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardRepository boardRepository;

    @PostMapping("/api/v1/boards")
    public Long save(@RequestBody BoardSaveRequestDto boardSaveRequestDto) {
        Board board = boardSaveRequestDto.toEntity();
        return boardRepository.save(board).getId();
    }

    @DeleteMapping("/api/v1/boards/{id}")
    public void delete(@RequestParam("id") Long id) {
        Board board = boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        boardRepository.delete(board);
    }

    @PutMapping("/api/v1/boards/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody BoardSaveRequestDto boardSaveRequestDto) {
        Board board = boardRepository.findById(id).orElseThrow(IllegalArgumentException::new);
        board.update(boardSaveRequestDto.getTitle(), boardSaveRequestDto.getContent());
    }

    @GetMapping("/api/v1/boards")
    public List<BoardListResponseDto> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardListResponseDto::new).collect(Collectors.toList());
    }

    @GetMapping(value = "/api/v2/boards", params = {"page", "size"})
    public PagedBoardListDto findAllWithPaging(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        PageRequest pageable = PageRequest.of(page, size);

        Page<BoardListResponseDto> responseDtoPage = boardRepository.findAll(pageable).map(BoardListResponseDto::new);

        return new PagedBoardListDto(
                responseDtoPage.getContent(),
                responseDtoPage.getPageable().getPageNumber(),
                responseDtoPage.getPageable().getPageSize(),
                responseDtoPage.getTotalElements());

    }
}
