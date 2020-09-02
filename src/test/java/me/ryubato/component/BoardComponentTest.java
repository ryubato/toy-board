package me.ryubato.component;

import me.ryubato.Fixtures;
import me.ryubato.domain.Board;
import me.ryubato.domain.BoardRepository;
import me.ryubato.web.BoardForm;
import me.ryubato.web.BoardListDto;
import me.ryubato.web.RestResponsePage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
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
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;

@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        properties = {"init-data=false"}
)
public class BoardComponentTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void 게시글_등록() throws Exception {
        //given
        String baseUrl = "http://localhost:" + port + "/api/v1/boards";

        BoardForm dto = Fixtures.aBoardForm().build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<BoardForm> request = new HttpEntity<>(dto, headers);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(baseUrl, request, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(1);
    }

    @Test
    void 게시글_삭제() {
        //given
        Board board = Fixtures.aBoard().build();
        boardRepository.save(board);

        String baseUrl = "http://localhost:" + port + "/api/v1/boards/" + Math.toIntExact(board.getId());

        //when
        restTemplate.delete(baseUrl);

        //then
        assertThatThrownBy(() -> {
            boardRepository.findById(board.getId()); })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void 게시글_목록조회_v1() {
        //given
        String baseUrl = "http://localhost:" + port + "/api/v1/boards";
        //when
        ResponseEntity<List<BoardListDto>> responseEntity =
                restTemplate.exchange(baseUrl, GET, null, new ParameterizedTypeReference<List<BoardListDto>>() {
                });
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void 게시글_목록조회_v2_페이징() {
        //given
        String baseUrl = "http://localhost:" + port + "/api/v2/boards?page=1&size=1";

        ParameterizedTypeReference<RestResponsePage<BoardListDto>> type = new ParameterizedTypeReference<RestResponsePage<BoardListDto>>() {
        };

        //when
        ResponseEntity<RestResponsePage<BoardListDto>> responseEntity =
                restTemplate.exchange(baseUrl, GET, null, type);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(responseEntity.getBody().getContent());
    }

    @Test
    void 게시글_목록조회_v3_페이징() {
        //given
        String baseUrl = "http://localhost:" + port + "/api/v3/boards";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("page", "1");
        params.add("size", "1");

        String uriString = UriComponentsBuilder.fromUriString(baseUrl)
                .queryParams(params).toUriString();

        ParameterizedTypeReference<RestResponsePage<BoardListDto>> type = new ParameterizedTypeReference<RestResponsePage<BoardListDto>>() {
        };

        //when
        ResponseEntity<RestResponsePage<BoardListDto>> responseEntity =
                restTemplate.exchange(uriString, GET, null, type);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        System.out.println(responseEntity.getBody().getContent());
    }
}