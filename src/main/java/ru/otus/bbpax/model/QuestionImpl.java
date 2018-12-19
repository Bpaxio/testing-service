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
        return correct.equals(answer - 1);
    }

    @Override
    public boolean isCorrect(String answer) {
        return answers.get(correct).equalsIgnoreCase(answer)
                || possibleIntegerAnswer(answer)
                && isCorrect(Integer.parseInt(answer));
    }

    @Override
    public boolean possibleAnswer(String answer) {
        boolean canBeInt = possibleIntegerAnswer(answer);
        return answers.stream().anyMatch(s -> s.equalsIgnoreCase(answer)) || canBeInt;
    }

    boolean possibleIntegerAnswer(String answer) {
        try {
            int intAnswer = Integer.parseInt(answer);
            return possibleAnswer(intAnswer);
        } catch (NumberFormatException ignored) {
            return false;
        }
    }

    boolean possibleAnswer(Integer answer) {
        return Objects.nonNull(answer) && answer > 0 && answer <= answers.size();
    }
}
