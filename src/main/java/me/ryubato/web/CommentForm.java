package me.ryubato.web;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
public class CommentForm {

    @NotBlank
    private String content;

    @NotBlank
    @Length(max = 20)
    private String writer;

    @Builder
    public CommentForm(@NotBlank String content, @NotBlank @Length(max = 20) String writer) {
        this.content = content;
        this.writer = writer;
    }
}
