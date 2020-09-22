package me.ryubato.domain;

import me.ryubato.Fixtures;
import me.ryubato.service.CommentService;
import me.ryubato.web.CommentForm;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentServiceTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentService commentService;

    @Test
    void save_childComment() {

        //given
        Board board = Fixtures.aBoard().build();
        boardRepository.save(board);

        Comment parentComment = Fixtures.aComment().board(board).build();
        commentRepository.save(parentComment);

        CommentForm commentForm = Fixtures.aCommentForm().build();

        //when
        commentService.saveComment(board.getId(), parentComment.getId(), commentForm);

        //then
        Comment resultComment = commentRepository.findById(parentComment.getId()).orElseThrow(IllegalArgumentException::new);
        Assertions.assertThat(resultComment.getChild().size()).isEqualTo(1);

    }

}