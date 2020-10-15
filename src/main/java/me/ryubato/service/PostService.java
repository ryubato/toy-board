package me.ryubato.service;

import lombok.RequiredArgsConstructor;
import me.ryubato.domain.Post;
import me.ryubato.domain.PostRepository;
import me.ryubato.web.CustomRestResponsePage;
import me.ryubato.web.PostForm;
import me.ryubato.web.PostListRspDto;
import me.ryubato.web.PostRspDto;
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
        Post post = Post.create(postForm.getTitle(), postForm.getWriter(), postForm.getContent(), postForm.getBoardId());
        return postRepository.save(post).getId();
    }

    public void delete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        postRepository.delete(post);
    }

    public void update(Long postId, PostForm postForm) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        post.update(postForm.getTitle(), postForm.getContent());
    }

    public PostRspDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        return new PostRspDto(post);
    }

    public List<PostListRspDto> getPosts() {
        return postRepository.findAll().stream().map(PostListRspDto::new).collect(Collectors.toList());
    }

    public List<PostListRspDto> getPostsByBoardId(Long boardId) {
        return postRepository.findByBoardId(boardId).stream().map(PostListRspDto::new).collect(Collectors.toList());
    }

    public Page<PostListRspDto> getPostsWithPagingDefaultType(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size)).map(PostListRspDto::new);
    }

    public CustomRestResponsePage<PostListRspDto> getPostsWithPagingCustomRestResponsePage(int page, int size) {
        Page<PostListRspDto> response = postRepository.findAll(PageRequest.of(page, size)).map(PostListRspDto::new);
        return new CustomRestResponsePage<>(response.getContent(), response.getNumber(), response.getSize(), response.getTotalElements());
    }
}
