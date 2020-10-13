package me.ryubato.web;

import lombok.RequiredArgsConstructor;
import me.ryubato.service.PostListDto;
import me.ryubato.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostApiController {

    private final PostService postService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostForm postForm) {  // TODO @Valid 적용
        return postService.save(postForm);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public void delete(@PathVariable("id") Long id) {
        postService.delete(id);
    }

    @PutMapping("/api/v1/posts/{id}")
    public void update(@PathVariable("id") Long id, @RequestBody PostForm postForm) {
        postService.update(id, postForm);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostDto findById(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @GetMapping("/api/v1/posts")
    public List<PostListDto> findAll() {
        return postService.getPosts();
    }

    @GetMapping(value = "/api/v2/posts", params = {"page", "size"})
    public Page<PostListDto> findAllWithPagingDefaultType(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return postService.getPostsWithPagingDefaultType(page, size);
    }

    @GetMapping(value = "/api/v3/posts", params = {"page", "size"})
    public CustomRestResponsePage<PostListDto> findAllWithPagingTypeCustomRestResponsePage(
            @RequestParam("page") int page,
            @RequestParam("size") int size) {
        return postService.getPostsWithPagingCustomRestResponsePage(page, size);
    }
}
