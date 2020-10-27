package me.ryubato.board.integration;

import me.ryubato.board.Fixtures;
import me.ryubato.board.domain.Post;
import me.ryubato.board.domain.PostRepository;
import me.ryubato.board.rest.PostListRspDto;
import me.ryubato.board.rest.PostForm;
import me.ryubato.board.rest.RestResponsePage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@AutoConfigureTestDatabase
public class PostComponentTest {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void 게시글_등록() throws Exception {
        //given
        String baseUrl = "http://localhost:" + port + "/api/v1/posts";

        PostForm dto = Fixtures.aPostForm().build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<PostForm> request = new HttpEntity<>(dto, headers);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(baseUrl, request, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(1);
    }

    @Test
    void 게시글_삭제() {
        //given
        Post post = Fixtures.aPost().build();
        postRepository.save(post);

        String baseUrl = "http://localhost:" + port + "/api/v1/posts/" + Math.toIntExact(post.getId());

        //when
        restTemplate.delete(baseUrl);

        //then
        assertThat(postRepository.findById(post.getId())).isEmpty();
    }

    @Test
    void 게시글_목록조회_v1() {
        //given
        String baseUrl = "http://localhost:" + port + "/api/v1/posts";
        //when
        ResponseEntity<List<PostListRspDto>> responseEntity =
                restTemplate.exchange(baseUrl, GET, null, new ParameterizedTypeReference<List<PostListRspDto>>() {
                });
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void 게시글_목록조회_v2_페이징() {
        //given
        String baseUrl = "http://localhost:" + port + "/api/v2/posts?page=1&size=1";

        ParameterizedTypeReference<RestResponsePage<PostListRspDto>> type = new ParameterizedTypeReference<RestResponsePage<PostListRspDto>>() {
        };

        //when
        ResponseEntity<RestResponsePage<PostListRspDto>> responseEntity =
                restTemplate.exchange(baseUrl, GET, null, type);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(responseEntity.getBody().getContent());
    }

    @Test
    void 게시글_목록조회_v3_페이징() {
        //given
        String baseUrl = "http://localhost:" + port + "/api/v3/posts";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", "1");
        params.add("size", "1");

        String uriString = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParams(params).toUriString();

        ParameterizedTypeReference<RestResponsePage<PostListRspDto>> type = new ParameterizedTypeReference<RestResponsePage<PostListRspDto>>() {
        };

        //when
        ResponseEntity<RestResponsePage<PostListRspDto>> responseEntity =
                restTemplate.exchange(uriString, GET, null, type);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(responseEntity.getBody().getContent());
    }
}