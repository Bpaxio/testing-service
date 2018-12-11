package ru.otus.bbpax.model.result;

public class QuestionResult implements ActionResult {
    private boolean wasSuccess;

    public boolean wasSuccess() {
        return wasSuccess;
    }

    public QuestionResult success() {
        wasSuccess = true;
        return this;
    }

    public QuestionResult failed() {
        wasSuccess = false;
        return this;
    }

    @Override
    public String getPresentableView() {
        return String.valueOf(wasSuccess);
    }
}
