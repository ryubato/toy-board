package me.ryubato.board.rest;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ryubato.board.domain.Post;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

import static com.fasterxml.jackson.annotation.JsonFormat.Shape;

@Data
@NoArgsConstructor
public class PostListRspDto {
    private Long id;
    private String category;
    private String title;
    private String writer;
    private Integer thumbsCount;
    private Integer viewCount;

    @DateTimeFormat(pattern = "HH:mm")
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
