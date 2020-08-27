package me.ryubato.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "PARENT_COMMENT_ID")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> child = new ArrayList<>();

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
