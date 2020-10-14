package me.ryubato.web;

import me.ryubato.domain.Post;

public class PostRespDto {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private Long boardId;

    public PostRespDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
        this.boardId = post.getBoardId();
    }
}
