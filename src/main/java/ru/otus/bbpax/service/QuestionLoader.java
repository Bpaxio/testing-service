package ru.otus.bbpax.service;

import java.util.List;

public interface QuestionLoader<T> {
    T getQuestionById(long id);
    List<T> getQuestions();
}
