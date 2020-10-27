package me.ryubato.board.rest;

import lombok.Data;
import me.ryubato.board.domain.Post;

import java.time.LocalDateTime;

@Data
public class PostListRspDto {
    private Long id;
    private String title;
    private String writer;
    private Long boardId;
    private LocalDateTime lastModifiedDate;

    public PostListRspDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.boardId = post.getBoardId();
        this.lastModifiedDate = post.getLastModifiedDate();
    }
}
