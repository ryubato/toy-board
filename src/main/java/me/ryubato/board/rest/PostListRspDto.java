package me.ryubato.board.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import me.ryubato.board.domain.Post;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Data
public class PostListRspDto {
    private Long id;
    private String category;
    private String title;
    private String writer;
    private Integer thumbsCount;
    private Integer viewCount;

    @JsonFormat(shape = Shape.STRING, pattern = "HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime modifiedTime;

    public PostListRspDto(Post post) {
        id = post.getId();
        category = post.getCategory();
        title = post.getTitle();
        writer = post.getWriter();
        thumbsCount = post.getThumbsCount();
        viewCount = post.getViewCount();
        modifiedTime = post.getLastModifiedDate();
    }
}
