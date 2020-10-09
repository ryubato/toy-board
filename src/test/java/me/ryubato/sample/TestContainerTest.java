package me.ryubato.sample;

import me.ryubato.Fixtures;
import me.ryubato.domain.Board;
import me.ryubato.domain.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@Testcontainers
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = NONE)
public class TestContainerTest {

    private final Logger log = LoggerFactory.getLogger(TestContainerTest.class);

    @Container
    static MySQLContainer mysql = new MySQLContainer();

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void initContainerTest() {

        log.info("container mysql databaseName : " + mysql.getDatabaseName());
        log.info("container mysql databaseName : " + mysql.getExposedPorts());

        Board board = Fixtures.aBoard().build();

        boardRepository.save(board);

        Assertions.assertThat(board.getId()).isEqualTo(1L);
    }

}
