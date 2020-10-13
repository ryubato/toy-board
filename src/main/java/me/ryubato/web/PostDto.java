package me.ryubato.web;

import me.ryubato.domain.Post;

public class PostDto {
    private Long id;
    private String title;
    private String writer;
    private String content;
    private Long boardId;

    public PostDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.content = post.getContent();
        this.boardId = post.getBoardId();
    }
}
