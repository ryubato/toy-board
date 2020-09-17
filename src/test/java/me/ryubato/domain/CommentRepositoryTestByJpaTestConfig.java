package me.ryubato.domain;

import me.ryubato.config.DataSourceJpaTestConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = DataSourceJpaTestConfig.class)
class CommentRepositoryTestByJpaTestConfig {

    @Autowired
    private CommentRepository commentRepository;

    @Test
    void findAll() {
        commentRepository.findAllByParent(Comment.builder().id(1L).build());
        commentRepository.findAllByParentUsingNativeQuery(1L);
    }
}