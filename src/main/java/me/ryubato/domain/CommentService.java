package me.ryubato.domain;

import lombok.RequiredArgsConstructor;
import me.ryubato.web.CommentForm;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void saveComment(Long boardId, Long parentCommentId, CommentForm commentForm) {

        Board board = boardRepository.findById(boardId).orElseThrow(IllegalArgumentException::new);

        Comment parentComment = null;
        if (parentCommentId != null) {
            parentComment = commentRepository.findById(parentCommentId).orElseThrow(IllegalArgumentException::new);
        }

        Comment comment = Comment.builder()
                .content(commentForm.getContent())
                .writer(commentForm.getWriter())
                .board(board)
                .parent(parentComment)
                .build();

        commentRepository.save(comment);
    }
}
