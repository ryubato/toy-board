package me.ryubato.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private String writer;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PARENT_COMMENT_ID")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

    @Builder
    public Comment(Long id, String content, String writer, Comment parent, List<Comment> child) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.parent = parent;
        this.child = child;
    }

    public static Comment createComment(String content, String writer, Comment parent) {
        return Comment.builder()
                .content(content)
                .writer(writer)
                .parent(parent)
                .build();
    }
}
