package me.ryubato.web;

import lombok.RequiredArgsConstructor;
import me.ryubato.domain.Board;
import me.ryubato.domain.BoardRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardRepository boardRepository;

    @PostMapping(value = "/api/v1/boards")
    public Long save(@RequestBody BoardSaveReqDto boardSaveReqDto) {
        Board board = boardSaveReqDto.toEntity();
        return boardRepository.save(board).getId();
    }

}
