package ru.otus.bbpax.model.result;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class NameResultTest {
    private final static String DEFAULT_NAME = "default_name";
    private final static String DEFAULT_SURNAME = "default_surname";


    private NameResult result;

    @BeforeEach
    void setUp() {
        result = new NameResult();
        result.setName(DEFAULT_NAME);
        result.setSurname(DEFAULT_SURNAME);
    }

    @Test
    void getSurname() {
        assertEquals(DEFAULT_SURNAME, result.getSurname());
    }

    @Test
    void setSurname() {
        String differ = "anotherSurname";
        result.setSurname(differ);
        assertAll(
                () -> assertEquals(differ, result.getSurname()),
                () -> assertEquals(DEFAULT_NAME, result.getName()));
    }

    @Test
    void getName() {
        assertEquals(DEFAULT_NAME, result.getName());
    }

    @Test
    void setName() {
        String differ = "anotherName";
        result.setName(differ);
        assertAll(
                () -> assertEquals(differ, result.getName()),
                () -> assertEquals(DEFAULT_SURNAME, result.getSurname()));
    }

    @Test
    void getPresentableView() {
        String presentableView = result.getPresentableView();
        assertAll(
                () -> assertNotEquals(DEFAULT_NAME, presentableView),
                () -> assertNotEquals(DEFAULT_SURNAME, presentableView),
                () -> assertThat(presentableView).contains(DEFAULT_NAME),
                () -> assertThat(presentableView).contains(DEFAULT_SURNAME),
                () -> assertEquals(DEFAULT_NAME + "   " + DEFAULT_SURNAME, presentableView));
    }
}