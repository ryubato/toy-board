package me.ryubato.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards")
@Getter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board {

    @Id @GeneratedValue
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String content;

    private String writer;

    @Column(updatable = false)
    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "board")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(Long id, String title, String content, String writer, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
}
