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
                .title("테스트")
                .content("안녕하세요.")
                .writer("admin");
    }

    public static CommentBuilder aComment() {
        return Comment.builder()
                .content("감사합니다.")
                .writer("guest");
    }

    public static BoardFormBuilder aBoardForm() {
        return BoardForm.builder()
                .title("테스트")
                .content("안녕하세요.")
                .writer("admin");
    }

    public static CommentFormBuilder aCommentForm() {
        return CommentForm.builder()
                .content("감사합니다.")
                .writer("guest");
    }
}
