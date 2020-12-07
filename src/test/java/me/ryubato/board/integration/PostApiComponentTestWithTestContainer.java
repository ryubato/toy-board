package me.ryubato.board.integration;

import me.ryubato.board.Fixtures;
import me.ryubato.board.domain.PostRepository;
import me.ryubato.board.rest.PostListRspDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
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
import org.testcontainers.utility.DockerImageName;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Disabled
@Testcontainers
@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class PostApiComponentTestWithTestContainer {

    private final Logger log = LoggerFactory.getLogger(PostApiComponentTestWithTestContainer.class);

    @Container
    private static final MySQLContainer MY_SQL_CONTAINER = new MySQLContainer(DockerImageName.parse("mysql:5.7.22"));

    @Autowired private PostRepository postRepository;
    @Autowired private TestRestTemplate restTemplate;

    @LocalServerPort private String port;

    @BeforeAll
    void init() {
        postRepository.save(Fixtures.aPost().build());
        postRepository.save(Fixtures.aPost().build());
    }

    @Test
    void getBoardsTest() {

        String baseUrl = "http://localhost:" + port + "/api/v1/posts";

        ResponseEntity<List<PostListRspDto>> responseEntity =
                restTemplate.exchange(baseUrl, HttpMethod.GET, null, new ParameterizedTypeReference<List<PostListRspDto>>() {});

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().size()).isEqualTo(2);

    }
}
