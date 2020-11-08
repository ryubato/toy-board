package me.ryubato.board.rest;

import me.ryubato.board.domain.Post;
import me.ryubato.board.service.PostService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.MockMvcBuilderCustomizer;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(Lifecycle.PER_CLASS)
@AutoConfigureDataJpa
@WebMvcTest(PostRestController.class)
@Import(PostRestControllerTestWithWebMvcTest.MockMvcConfig.class)
class PostRestControllerTestWithWebMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PostService postService;

    @Test
    void findAll() throws Exception {
        /* given */
        Post aPost = Post.builder()
                .boardId(1L)
                .id(1L)
                .title("공지사항")
                .content("테스트 입니다.")
                .build();

        PostListRspDto postListRspDto = new PostListRspDto(aPost);
        List<PostListRspDto> postListRspDtos = Collections.singletonList(postListRspDto);

        given(postService.getPosts()).willReturn(postListRspDtos);

        /* when */
        mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id", equalTo(1)));

    }

    @TestConfiguration
    static class MockMvcConfig {
        @Bean
        MockMvcBuilderCustomizer utf8Config() {
            return builder -> builder.addFilters(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                    .alwaysDo(print());
        }
    }
}