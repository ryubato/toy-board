package me.ryubato.web;

import lombok.Data;
import me.ryubato.domain.Post;

import java.time.LocalDateTime;

@Data
public class PostRspDto {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private Long boardId;
    private LocalDateTime lastModifiedDate;

    public PostRspDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
        this.boardId = post.getBoardId();
        this.lastModifiedDate = post.getLastModifiedDate();
    }
}
