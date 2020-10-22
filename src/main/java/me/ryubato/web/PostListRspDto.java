package me.ryubato.web;

import lombok.Data;
import me.ryubato.domain.Post;

@Data
public class PostListRspDto {
    private Long id;
    private String title;
    private String writer;
    private Long boardId;

    public PostListRspDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.boardId = post.getBoardId();
    }
}
