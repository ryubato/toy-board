package me.ryubato.service;

import me.ryubato.domain.Post;

public class PostListDto {
    private Long id;
    private String title;
    private String writer;
    private Long boardId;

    public PostListDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.boardId = post.getBoardId();
    }
}
