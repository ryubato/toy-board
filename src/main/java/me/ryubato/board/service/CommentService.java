package me.ryubato.board.service;

import lombok.RequiredArgsConstructor;
import me.ryubato.board.domain.Comment;
import me.ryubato.board.domain.CommentRepository;
import me.ryubato.board.rest.CommentForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    @Transactional
    public void saveComment(Long postId, Long parentCommentId, CommentForm commentForm) {

        Comment parentComment = null;

        if (parentCommentId != null) {
            parentComment = commentRepository.findById(parentCommentId).orElseThrow(IllegalArgumentException::new);
        }

        Comment comment = Comment.builder()
                .content(commentForm.getContent())
                .writer(commentForm.getWriter())
                .parent(parentComment)
                .build();

        commentRepository.save(comment);
    }
}
