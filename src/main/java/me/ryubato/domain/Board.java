package me.ryubato.domain;

import lombok.*;
import me.ryubato.config.BaseTimeEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boards")
@Getter
@EqualsAndHashCode(of = "id", callSuper = false)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_id")
    private Long id;

    private String title;

    private String content;

    @Column(nullable = false, updatable = false)
    private String writer;

    private Long viewCount;

    private Long recommendationCount; // TODO 추천한 사용자와의 연관관계 필요?

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "board_id")
    private List<Comment> comments = new ArrayList<>();

    @Builder
    public Board(Long id, String title, String content, String writer, Long viewCount, Long recommendationCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.viewCount = viewCount;
        this.recommendationCount = recommendationCount;
    }

    public static Board createBoard(String title, String content, String writer) {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public void view() {
        this.viewCount += 1;
    }

    public void recommend() {
        this.recommendationCount += 1;
    }

    public void cancelRecommend() {
        this.recommendationCount -= 1;
    }
}
