package ru.otus.bbpax.model;

public interface Question {

    String getPresentableView();

    boolean isCorrect(Integer answer);

    boolean isCorrect(String answer);
}
