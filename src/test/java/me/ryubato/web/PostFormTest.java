package me.ryubato.web;

import me.ryubato.Fixtures;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(Lifecycle.PER_CLASS)
public class PostFormTest {

    @BeforeAll
    void setup() {
        // Locale 설정에 따라 메세지가 다르다.
        Locale.setDefault(Locale.KOREA);
    }

    @Test
    void postForm_validation_test() {

        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

        PostForm postForm = Fixtures.aPostForm()
                .title("123456789012345678901234567890")
                .content("")
                .build();

        Set<ConstraintViolation<PostForm>> constraintViolations = validator.validate(postForm);

        assertEquals(2, constraintViolations.size());

        assertEquals("길이가 0에서 20 사이여야 합니다",
                constraintViolations.stream()
                        .filter(c -> c.getPropertyPath().toString().equals("title"))
                        .findAny().orElse(null).getMessage());

        assertEquals("공백일 수 없습니다",
                constraintViolations.stream()
                        .filter(c -> c.getPropertyPath().toString().equals("content"))
                        .findAny().orElse(null).getMessage());
    }
}
