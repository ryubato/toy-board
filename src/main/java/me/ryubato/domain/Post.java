package me.ryubato.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ryubato.config.BaseTimeEntity;

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
    private Integer thumbsUp;

    @Column
    private Integer viewCount;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Post(Long id, Long boardId, String category, String title, String writer, String content, Integer thumbsUp, Integer viewCount) {
        this.id = id;
        this.boardId = boardId;
        this.category = category;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.thumbsUp = thumbsUp;
        this.viewCount = viewCount;
    }

    public static Post create(String title, String writer, String content, Long boardId) {
        return Post.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .boardId(boardId)
                .build();
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
