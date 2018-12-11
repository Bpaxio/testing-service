package ru.otus.bbpax.model.action;

import ru.otus.bbpax.model.Question;
import ru.otus.bbpax.model.result.QuestionResult;
import ru.otus.bbpax.service.ConsoleAdapter;

public class QuestionAction implements DialogAction<QuestionResult> {
    private final Question question;
    private final ConsoleAdapter adapter;

    private QuestionResult result;

    public QuestionAction(ConsoleAdapter adapter, Question question) {
        this.question = question;
        this.adapter = adapter;
        result = new QuestionResult().failed();
    }

    @Override
    public QuestionResult run() {
        adapter.sendMessage(question.getPresentableView());

        String answer = adapter.getInput();

        if (question.isCorrect(answer)) {
            result.success();
            return result;
        } else if (!question.possibleAnswer(answer)) {
            return repeatQuestion();
        }
        return result;
    }

    private QuestionResult repeatQuestion() {
        adapter.sendMessage("Please enter one of represented answers");
        return run();
    }


    private boolean validateAnswer(String answer) {

        return false;
    }

    @Override
    public QuestionResult getResult() {
        return result;
    }
}
