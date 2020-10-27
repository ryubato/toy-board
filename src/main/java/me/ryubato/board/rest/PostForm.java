package me.ryubato.board.rest;

import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
public class PostForm {

    @Length(min = 1, max = 20)
    private String title;
    @NotEmpty
    private String writer;
    @NotEmpty
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
