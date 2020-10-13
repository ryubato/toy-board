package me.ryubato.web;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostForm {

    private String title;
    private String writer;
    private String content;
    private Long boardId;

    @Builder
    public PostForm(String title, String writer, String content, Long boardId) {
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.boardId = boardId;
    }

}
