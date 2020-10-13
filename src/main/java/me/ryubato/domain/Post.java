package me.ryubato.domain;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.ryubato.config.BaseTimeEntity;

import javax.persistence.*;

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
    private String title;

    @Column
    private String writer;

    @Column
    private String content;

    @Column
    private Long boardId;

    @Builder
    public Post(Long id, String title, String writer, String content, Long boardId) {
        this.id = id;
        this.title = title;
        this.writer = writer;
        this.content = content;
        this.boardId = boardId;
    }

    public static Post createPost(String title, String writer, String content, Long boardId) {
        return Post.builder()
                .title(title)
                .writer(writer)
                .content(content)
                .boardId(boardId)
                .build();
    }

    public void updatePost(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
