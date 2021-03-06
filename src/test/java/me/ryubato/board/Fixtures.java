package me.ryubato.board;

import me.ryubato.board.domain.Board;
import me.ryubato.board.domain.Comment;
import me.ryubato.board.domain.Post;
import me.ryubato.board.rest.PostForm;

public class Fixtures {

    public static Board.BoardBuilder aBoard() {
        return Board.builder()
                .name("자유게시판");
    }

    public static Comment.CommentBuilder aComment() {
        return Comment.builder()
                .writer("손님")
                .content("감사합니다.");
    }

    public static PostForm.PostFormBuilder aPostForm() {
        return PostForm.builder()
                .title("환영합니다.")
                .writer("관리자")
                .content("");
    }

    public static Post.PostBuilder aPost() {
        return Post.builder()
                .title("환영합니다.")
                .writer("관리자")
                .content("");
    }
}
