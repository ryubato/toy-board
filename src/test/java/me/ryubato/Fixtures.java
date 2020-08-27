package me.ryubato;

import me.ryubato.domain.Board;
import me.ryubato.domain.Board.BoardBuilder;
import me.ryubato.domain.Comment;
import me.ryubato.domain.Comment.CommentBuilder;

public class Fixtures {

    public BoardBuilder aBoard() {
        return Board.builder()
                .id(1L)
                .title("제목")
                .content("내용")
                .writer("관리자");
    }

    public CommentBuilder aComment() {
        return Comment.builder()
                .id(1L)
                .content("감사합니다.")
                .writer("손님");
    }
}
