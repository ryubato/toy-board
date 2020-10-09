package me.ryubato.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@DataJpaTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findAll() {

        commentRepository.findAllByParentNoNative(Comment.builder().id(1L).build());

        commentRepository.findAllByParentNative(1L);
    }

    @Test
    void findChildComments() {

        Comment comment = commentRepository.findById(3L)
                .orElseThrow(IllegalArgumentException::new);

        List<Comment> childComments = comment.getChild();

        childComments.stream().forEach(c -> System.out.println(c.toString()));

    }
}