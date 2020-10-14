package me.ryubato.service;

import lombok.RequiredArgsConstructor;
import me.ryubato.domain.Post;
import me.ryubato.domain.PostRepository;
import me.ryubato.web.CustomRestResponsePage;
import me.ryubato.web.PostRespDto;
import me.ryubato.web.PostForm;
import me.ryubato.web.PostListRespDto;
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

    public PostRespDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        return new PostRespDto(post);
    }

    public List<PostListRespDto> getPosts() {
        return postRepository.findAll().stream().map(PostListRespDto::new).collect(Collectors.toList());
    }

    public Page<PostListRespDto> getPostsWithPagingDefaultType(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size)).map(PostListRespDto::new);
    }

    public CustomRestResponsePage<PostListRespDto> getPostsWithPagingCustomRestResponsePage(int page, int size) {
        Page<PostListRespDto> response = postRepository.findAll(PageRequest.of(page, size)).map(PostListRespDto::new);
        return new CustomRestResponsePage<>(response.getContent(), response.getNumber(), response.getSize(), response.getTotalElements());
    }

    public List<PostListRespDto> getPostsByBoardId(Long boardId) {
        return postRepository.findByBoardId(boardId).stream().map(PostListRespDto::new).collect(Collectors.toList());
    }
}
