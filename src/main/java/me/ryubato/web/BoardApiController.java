package me.ryubato.web;

import lombok.RequiredArgsConstructor;
import me.ryubato.domain.Board;
import me.ryubato.domain.BoardRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/api/v1/boards")
    public List<BoardListResponseDto> findAll() {
        return boardRepository.findAll().stream()
                .map(BoardListResponseDto::new).collect(Collectors.toList());
    }
}
