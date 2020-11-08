package me.ryubato.board.rest;

import me.ryubato.board.domain.Post;
import me.ryubato.board.service.PostService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.BDDMockito;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@TestInstance(Lifecycle.PER_CLASS)
class PostRestControllerTest {

    private MockMvc mockMvc;
    private PostService postService;

    @BeforeAll
    void setup() {
        postService = mock(PostService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new PostRestController(postService))
                .addFilters(new CharacterEncodingFilter("UTF-8", true))
                .alwaysDo(print())
                .build();
    }

    @Test
    void findAll() throws Exception {
        /* given */
        Post aPost = Post.builder()
                .boardId(1L)
                .id(1L)
                .title("공지사항")
                .writer("관리자")
                .build();

        PostListRspDto postListRspDto = new PostListRspDto(aPost);

        List<PostListRspDto> postListRspDtos = Collections.singletonList(postListRspDto);
        BDDMockito.given(postService.getPosts()).willReturn(postListRspDtos);

        /* when then */
        mockMvc.perform(get("/api/v1/posts"))
                .andExpect(status().isOk());
    }
}