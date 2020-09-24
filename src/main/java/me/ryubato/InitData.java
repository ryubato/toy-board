package me.ryubato;

import lombok.RequiredArgsConstructor;
import me.ryubato.domain.Board;
import me.ryubato.domain.BoardRepository;
import me.ryubato.domain.Comment;
import me.ryubato.domain.CommentRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@ConditionalOnProperty(name = "init-data", havingValue = "true", matchIfMissing = true)
@RequiredArgsConstructor
public class InitData {

    private final BoardRepository boardRepository;
    private final CommentRepository commentRepository;

    @PostConstruct
    private void postConstruct() {

        Board board = null;
        Comment comment = null;

        for (int i = 0; i < 2; i++) {
            Comment parent = null;

            board = getBoard();
            boardRepository.save(board);

            for (int j = 0; j < 5; j++) {
                comment = getComment(board, parent);
                if (j == 2) {
                    parent = comment;
                }
                commentRepository.save(comment);
            }
        }
    }

    private Board getBoard() {
        return Board.builder()
                    .title("테스트")
                    .writer("관리자")
                    .content("테스트 입니다.")
                    .build();
    }

    private Comment getComment(Board board, Comment parent) {
        return Comment.builder()
                .content("감사합니다.")
                .writer("손님")
                .parent(parent)
                .build();
    }
}
