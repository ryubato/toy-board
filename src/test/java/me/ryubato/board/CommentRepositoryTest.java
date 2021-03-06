package me.ryubato.board;

import me.ryubato.board.domain.Comment;
import me.ryubato.board.domain.CommentRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@AutoConfigureTestDatabase
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findAll() {
        commentRepository.findAllByParent(Comment.builder().id(1L).build());
        commentRepository.findAllByParentUsingNativeQuery(1L);
    }

    @Test
    void findChildComments() {

        Comment comment = commentRepository.findById(3L)
                .orElseThrow(IllegalArgumentException::new);

        List<Comment> childComments = comment.getChild();

        childComments.stream().forEach(c -> System.out.println(c.toString()));

    }
}