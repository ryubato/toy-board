package me.ryubato;

import me.ryubato.domain.Board;
import me.ryubato.domain.Board.BoardBuilder;
import me.ryubato.domain.Comment;
import me.ryubato.domain.Comment.CommentBuilder;
import me.ryubato.web.BoardForm;
import me.ryubato.web.BoardForm.BoardFormBuilder;
import me.ryubato.web.CommentForm;
import me.ryubato.web.CommentForm.CommentFormBuilder;

public class Fixtures {

    public static BoardBuilder aBoard() {
        return Board.builder()
                .id(1L)
                .title("제목")
                .content("내용")
                .writer("관리자");
    }

    public static CommentBuilder aComment() {
        return Comment.builder()
                .id(1L)
                .content("감사합니다.")
                .writer("손님");
    }

    public static BoardFormBuilder aBoardForm() {
        return BoardForm.builder()
                .title("제목")
                .content("내용")
                .writer("관리자");
    }

    public static CommentFormBuilder aCommentForm() {
        return CommentForm.builder()
                .content("감사합니다.")
                .writer("손님");
    }
}
