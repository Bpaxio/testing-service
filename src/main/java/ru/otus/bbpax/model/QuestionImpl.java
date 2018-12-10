package ru.otus.bbpax.model;

import java.util.List;
import java.util.Objects;

public class QuestionImpl implements Question {
    private String title;
    private List<String> answers;
    private Integer correct;

    public QuestionImpl() {
    }

    public QuestionImpl(String title, List<String> answers, Integer correct) {
        this.title = title;
        this.answers = answers;
        this.correct = correct;
    }

    @Override
    public String getPresentableView() {
        StringBuffer sb = new StringBuffer();
        sb.append(title).append("\n");
        answers.forEach(s -> sb.append(s).append("    "));
        return sb.toString();
    }

    @Override
    public boolean isCorrect(Integer answer) {
        return Objects.equals(correct, answer);
    }

    @Override
    public boolean isCorrect(String answer) {
        return Objects.equals(answers.get(correct), answer);
    }
}
