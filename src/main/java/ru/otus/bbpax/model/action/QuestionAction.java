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

        if (question.possibleAnswer(answer)) {
            if (question.isCorrect(answer))
                return result.success();
        } else {
            return repeatQuestion();
        }
        return result;
    }

    private QuestionResult repeatQuestion() {
        adapter.sendLocalizedMessage("question.repeat");
        return run();
    }

    @Override
    public QuestionResult getResult() {
        return result;
    }
}
