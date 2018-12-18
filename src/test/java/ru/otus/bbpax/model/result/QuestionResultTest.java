package ru.otus.bbpax.model.result;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class QuestionResultTest {
    private QuestionResult result;

    @BeforeEach
    void setUp() {
        result = new QuestionResult();
    }

    @Test
    void wasSuccess() {
        assertThat(result.wasSuccess()).isFalse();
        result.success();
        assertThat(result.wasSuccess()).isTrue();
    }

    @Test
    void success() {
        assertThat(result.success()).isEqualTo(result);
        assertThat(result.wasSuccess()).isTrue();

    }

    @Test
    void failed() {
        assertThat(result.failed()).isEqualTo(result);
        assertThat(result.wasSuccess()).isFalse();
    }

    @Test
    void getPresentableView() {
        result.failed();
        assertThat(result.getPresentableView()).isEqualTo("false");
        result.success();
        assertThat(result.getPresentableView()).isEqualTo("true");
    }
}