package me.ryubato.component;

import me.ryubato.web.BoardListResponseDto;
import me.ryubato.web.BoardSaveRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpMethod.GET;

@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        properties = {"init-data=false"}
)
public class BoardComponentTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @LocalServerPort
    private int port;

    @Test
    void 게시글_정상등록() throws Exception {

        //given
        String baseUrl = "http://localhost:" + port + "/api/v1/boards";

        BoardSaveRequestDto dto = BoardSaveRequestDto
                .builder()
                .title("테스트")
                .content("테스트")
                .writer("테스트")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<BoardSaveRequestDto> request = new HttpEntity<>(dto, headers);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(baseUrl, request, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(1);
    }

    @Test
    void 게시글_목록조회_v1() {

        //given
        String baseUrl = "http://localhost:" + port + "/api/v1/boards";

        //when
        ResponseEntity<List<BoardListResponseDto>> responseEntity = restTemplate.exchange(baseUrl, GET, null, new ParameterizedTypeReference<List<BoardListResponseDto>>() {
        });

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);


    }
}
