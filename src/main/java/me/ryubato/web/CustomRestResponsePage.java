package me.ryubato.web;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

public class CustomRestResponsePage<T> extends PageImpl<T> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CustomRestResponsePage(
            @JsonProperty("content") List<T> content,
            @JsonProperty("number") int number,
            @JsonProperty("size") int size,
            @JsonProperty("totalElements") Long totalElements
    ) {
        super(content, PageRequest.of(number, size), totalElements);
    }

    public CustomRestResponsePage(List<T> content) {
        super(content);
    }

    public CustomRestResponsePage() {
        super(new ArrayList<>());
    }
}