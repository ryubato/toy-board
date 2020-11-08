package me.ryubato.board.integration;

import me.ryubato.board.domain.Post;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.net.URI;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PostIntegrationTest {

    @Autowired MockMvc mockMvc;
    @Autowired EntityManager em;

    @Test
    @Transactional
    public void 게시판글조회_withMockMvc() throws Exception {

        Post post = Post.builder().title("test").build();

        em.persist(post);
        em.flush();
        em.clear();

        Long postId = post.getId();

        final String baseUrl = "/api/v1/posts/" + postId;

        // when
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders
                .get(new URI(baseUrl))
                .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print()).andReturn();

        // then
        MockHttpServletResponse response = mvcResult.getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());

    }
}
