package me.ryubato.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "comments")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    private String content;

    private String writer;

    private LocalDateTime createdDate;

    private Comment parentComment;

    private Comment childComment;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    @Builder
    public Comment(Long id, String content, String writer, LocalDateTime createdDate, Comment parentComment, Comment childComment, Board board) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.createdDate = createdDate;
        this.parentComment = parentComment;
        this.childComment = childComment;
        this.board = board;
    }
}
