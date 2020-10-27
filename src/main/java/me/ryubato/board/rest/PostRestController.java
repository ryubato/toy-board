package me.ryubato.board.rest;

import lombok.RequiredArgsConstructor;
import me.ryubato.board.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostRestController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostForm postForm) {
        return postService.save(postForm);
    }

    @DeleteMapping("/api/v1/posts/{postId}")
    public void delete(@PathVariable("postId") Long id) {
        postService.delete(id);
    }

    @PutMapping("/api/v1/posts/{postId}")
    public void update(@PathVariable("postId") Long id, @RequestBody PostForm postForm) {
        postService.update(id, postForm);
    }

    @GetMapping("/api/v1/posts/{postId}")
    public PostRspDto findById(@PathVariable("postId") Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/api/v1/posts")
    public List<PostListRspDto> findAll() {
        return postService.getPosts();
    }

    @GetMapping("/api/v1/boards/{boardId}/posts")
    public List<PostListRspDto> findPostByBoardId(@PathVariable("boardId") Long boardId) {
        return postService.getPostsByBoardId(boardId);
    }

    @GetMapping(value = "/api/v2/posts", params = {"page", "size"})
    public Page<PostListRspDto> findAllWithPagingDefaultType(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return postService.getPostsWithPagingDefaultType(page, size);
    }

    @GetMapping(value = "/api/v3/posts", params = {"page", "size"})
    public CustomRestResponsePage<PostListRspDto> findAllWithPagingTypeCustomRestResponsePage(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return postService.getPostsWithPagingCustomRestResponsePage(page, size);
    }
}
