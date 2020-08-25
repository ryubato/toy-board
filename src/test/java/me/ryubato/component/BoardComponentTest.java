package me.ryubato.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.ryubato.web.BoardSaveReqDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(
        webEnvironment = RANDOM_PORT,
        properties = {"init-data=false"}
)
public class BoardComponentTest {

    @Autowired private TestRestTemplate restTemplate;
    @LocalServerPort private int port;

    @Test
    void 게시글_정상_등록시_board_id_반환() throws Exception {

        //given
        String baseUrl = "http://localhost:" + port + "/api/v1/boards";

        BoardSaveReqDto dto = BoardSaveReqDto
                .builder()
                .title("테스트")
                .content("테스트")
                .writer("테스트")
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<BoardSaveReqDto> request = new HttpEntity<>(dto, headers);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(baseUrl, request, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo(1);
    }
}
