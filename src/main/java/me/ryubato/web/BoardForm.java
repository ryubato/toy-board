package me.ryubato.web;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.ryubato.domain.Board;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class BoardForm {

    @NotBlank
    @Length(max = 20)
    private String title;

    @NotBlank
    private String content;

    @NotBlank
    @Length(max = 20)
    private String writer;

    @Builder
    public BoardForm(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }
}
