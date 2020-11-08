package me.ryubato.board.rest;

import me.ryubato.board.domain.Post;
import me.ryubato.board.rest.PostListRspDto;
import me.ryubato.board.rest.PostRestController;
import me.ryubato.board.service.PostService;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.filter.CharacterEncodingFilter;

import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class PostRestControllerTestByJUnit4 {

    private static MockMvc mockMvc;
    private static PostService postService;

    @BeforeClass
    public static void setup() {
        postService = Mockito.mock(PostService.class);
        mockMvc = MockMvcBuilders.standaloneSetup(new PostRestController(postService))
                .addFilter(new CharacterEncodingFilter(StandardCharsets.UTF_8.name(), true))
                .alwaysDo(print())
                .build();
    }

    @Test
    public void findAll() throws Exception {
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