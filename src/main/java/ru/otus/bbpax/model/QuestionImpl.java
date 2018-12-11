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
        // TODO: 2018-12-11 add answer variant, like a),b),c)... or 1),2),3)...
        answers.forEach(s -> sb.append(s).append("    "));
        return sb.toString();
    }

    @Override
    public boolean isCorrect(Integer answer) {
        if (answer >= answers.size()) {
            return false;
        }
        return isCorrect(answers.get(answer));
    }

    @Override
    public boolean isCorrect(String answer) {
        return answers.get(correct).equalsIgnoreCase(answer);
    }

    @Override
    public boolean possibleAnswer(String answer) {
        return answers.stream().anyMatch(s -> s.equalsIgnoreCase(answer));
    }
}
