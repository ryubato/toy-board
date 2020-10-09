package me.ryubato.domain;

import me.ryubato.Fixtures;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class BoardTest {

    @Autowired private BoardRepository boardRepository;

    @Test
    @Transactional
    void auditingBaseTimeEntityTest() throws Exception {

        //given
        Board board = Fixtures.aBoard().id(null).build();

        //when
        boardRepository.save(board);

        //then
        assertThat(board.getCreatedDate()).isInstanceOf(LocalDateTime.class);
        assertThat(board.getLastModifiedDate()).isInstanceOf(LocalDateTime.class);
    }
}