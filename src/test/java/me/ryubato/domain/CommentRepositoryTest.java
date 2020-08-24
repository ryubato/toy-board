package me.ryubato.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void test() {

        commentRepository.findAllByParentNoNative(Comment.builder().id(1L).build());

        commentRepository.findAllByParentNative(1L);
    }

}