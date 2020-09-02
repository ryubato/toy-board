package me.ryubato.domain;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String content;

    @Column(nullable = false, updatable = false)
    private String writer;

    private Long viewCount;

    private Long recommendationCount; // TODO 추천한 사용자와의 연관관계 필요?

    @Column(updatable = false)
    @CreationTimestamp
    private LocalDateTime createdDate;

    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(Long id, String title, String content, String writer, Long viewCount, Long recommendationCount, LocalDateTime createdDate, LocalDateTime modifiedDate, List<Comment> comments) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = viewCount;
        this.recommendationCount = recommendationCount;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.comments = comments;
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void viewBoard() {
        this.viewCount += 1;
    }

    public void recommendBoard() {
        this.recommendationCount += 1;
    }
}
