package me.ryubato.board.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ryubato.board.rest.PostForm;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static lombok.AccessLevel.PROTECTED;

@Entity
@Table(name = "posts")
@Getter
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = PROTECTED)
public class Post extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @Column
    private Long boardId;

    @Column
    private String category;

    @Column
    private String title;

    @Column
    private String writer;

    @Column
    private String content;

    @Column
    private Integer thumbsCount;

    @Column
    private Integer viewCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(Long id, Long boardId, String category, String title, String writer, String content, Integer thumbsCount, Integer viewCount) {
        this.id = id;
        this.boardId = boardId;
        this.category = category;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.thumbsCount = thumbsCount;
        this.viewCount = viewCount;
    }

    public static Post create(PostForm postForm) {
        return Post.builder()
                .boardId(postForm.getBoardId())
                .category(postForm.getContent())
                .title(postForm.getTitle())
                .writer(postForm.getWriter())
                .content(postForm.getContent())
                .thumbsCount(0)
                .viewCount(0)
                .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
