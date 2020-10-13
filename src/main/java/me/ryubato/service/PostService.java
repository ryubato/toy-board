package me.ryubato.service;

import lombok.RequiredArgsConstructor;
import me.ryubato.domain.Post;
import me.ryubato.domain.PostRepository;
import me.ryubato.web.CustomRestResponsePage;
import me.ryubato.web.PostDto;
import me.ryubato.web.PostForm;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Long save(PostForm postForm) {
        Post post = Post.createPost(postForm.getTitle(), postForm.getWriter(), postForm.getContent(), postForm.getBoardId());
        return postRepository.save(post).getId();
    }

    public void delete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        postRepository.delete(post);
    }

    public void update(Long postId, PostForm postForm) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        post.updatePost(postForm.getTitle(), postForm.getContent());
    }

    public PostDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        return new PostDto(post);
    }

    public List<PostListDto> getPosts() {
        return postRepository.findAll().stream().map(PostListDto::new).collect(Collectors.toList());
    }

    public Page<PostListDto> getPostsWithPagingDefaultType(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size)).map(PostListDto::new);
    }

    public CustomRestResponsePage<PostListDto> getPostsWithPagingCustomRestResponsePage(int page, int size) {
        Page<PostListDto> response = postRepository.findAll(PageRequest.of(page, size)).map(PostListDto::new);
        return new CustomRestResponsePage<>(response.getContent(), response.getNumber(), response.getSize(), response.getTotalElements());
    }

}
