package me.ryubato.web;

import me.ryubato.domain.Post;

public class PostListRespDto {
    private Long id;
    private String title;
    private String writer;
    private Long boardId;

    public PostListRespDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writer = post.getWriter();
        this.boardId = post.getBoardId();
    }
}
