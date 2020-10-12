package me.ryubato.component;

import me.ryubato.Fixtures;
import me.ryubato.domain.BoardRepository;
import me.ryubato.web.BoardListDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Testcontainers
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class BoardApiComponentTestWithTestContainer {

    private final Logger log = LoggerFactory.getLogger(BoardApiComponentTestWithTestContainer.class);

    @Container
    static MySQLContainer mysql = new MySQLContainer();

    @Autowired private BoardRepository boardRepository;
    @Autowired private TestRestTemplate restTemplate;
    @LocalServerPort private String port;

    @BeforeAll
    void init() {

        boardRepository.save(Fixtures.aBoard().build());
        boardRepository.save(Fixtures.aBoard().build());

    }

    @Test
    void getBoardsTest() {

        String baseUrl = "http://localhost:" + port + "/api/v1/boards";

        ResponseEntity<List<BoardListDto>> responseEntity =
                restTemplate.exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<BoardListDto>>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(2);

    }
}
