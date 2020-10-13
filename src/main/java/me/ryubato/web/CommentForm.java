package me.ryubato.web;

import lombok.Builder;
import lombok.Data;

@Data
public class CommentForm {

    private String content;
    private String writer;

    @Builder
    public CommentForm(String content, String writer) {
        this.content = content;
        this.writer = writer;
    }
}
