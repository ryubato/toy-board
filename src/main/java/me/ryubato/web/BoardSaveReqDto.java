package me.ryubato.web;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ryubato.domain.Board;

@Getter
@NoArgsConstructor
public class BoardSaveReqDto {

    private String title;
    private String content;
    private String writer;

    @Builder
    public BoardSaveReqDto(String title, String content, String writer) {
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
