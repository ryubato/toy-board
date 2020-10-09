package me.ryubato.web;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ryubato.domain.Board;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class BoardListDto {

    private Long id;
    private String title;
    private String writer;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifiedDate;

    @Builder
    public BoardListDto(Long id, String title, String writer, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.modifiedDate = modifiedDate;
    }

    public BoardListDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.writer = board.getWriter();
        this.modifiedDate = board.getLastModifiedDate();
    }
}
