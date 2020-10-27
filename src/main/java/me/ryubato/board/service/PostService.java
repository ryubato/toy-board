package me.ryubato.board.service;

import lombok.RequiredArgsConstructor;
import me.ryubato.board.domain.Post;
import me.ryubato.board.domain.PostRepository;
import me.ryubato.board.rest.CustomRestResponsePage;
import me.ryubato.board.rest.PostForm;
import me.ryubato.board.rest.PostListRspDto;
import me.ryubato.board.rest.PostRspDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    @Transactional
    public Long save(PostForm postForm) {
        Post post = Post.create(postForm.getTitle(), postForm.getWriter(), postForm.getContent(), postForm.getBoardId());
        return postRepository.save(post).getId();
    }

    @Transactional
    public void delete(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        postRepository.delete(post);
    }

    @Transactional
    public void update(Long postId, PostForm postForm) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        post.update(postForm.getTitle(), postForm.getContent());
    }

    @Transactional(readOnly = true)
    public PostRspDto getPost(Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(IllegalArgumentException::new);
        return new PostRspDto(post);
    }

    @Transactional(readOnly = true)
    public List<PostListRspDto> getPosts() {
        return postRepository.findAll().stream().map(PostListRspDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PostListRspDto> getPostsByBoardId(Long boardId) {
        return postRepository.findByBoardId(boardId).stream().map(PostListRspDto::new).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<PostListRspDto> getPostsWithPagingDefaultType(int page, int size) {
        return postRepository.findAll(PageRequest.of(page, size)).map(PostListRspDto::new);
    }

    @Transactional(readOnly = true)
    public CustomRestResponsePage<PostListRspDto> getPostsWithPagingCustomRestResponsePage(int page, int size) {
        Page<PostListRspDto> response = postRepository.findAll(PageRequest.of(page, size)).map(PostListRspDto::new);
        return new CustomRestResponsePage<>(response.getContent(), response.getNumber(), response.getSize(), response.getTotalElements());
    }
}
