package me.ryubato.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @OneToOne(fetch = LAZY)
    private Comment parent;

    @Builder
    public Comment(Long id, String content, String writer, LocalDateTime modifiedDate, Board board, Comment parent) {
        this.id = id;
        this.content = content;
        this.writer = writer;
        this.modifiedDate = modifiedDate;
        this.board = board;
        this.parent = parent;
    }

}
