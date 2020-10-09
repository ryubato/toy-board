package me.ryubato.web;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ryubato.domain.Board;

@Data
@NoArgsConstructor
public class BoardSaveRequestDto {

    private String title;
    private String content;
    private String writer;

    @Builder
    public BoardSaveRequestDto(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
