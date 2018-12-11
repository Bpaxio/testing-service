package ru.otus.bbpax.service;

import java.util.List;

public interface QuestionLoader<T> {
    T getQuestionById(int id);
    List<T> getQuestions();
}
