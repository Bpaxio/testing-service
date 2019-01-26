package ru.otus.bbpax.model.result;

import ru.otus.bbpax.model.Examinee;

public class QuestionResult implements ActionResult {

    private boolean wasSuccess;

    public QuestionResult success() {
        wasSuccess = true;
        return this;
    }

    public QuestionResult failed() {
        wasSuccess = false;
        return this;
    }

    public boolean wasSuccess() {
        return wasSuccess;
    }

    @Override
    public String getPresentableView() {
        return String.valueOf(wasSuccess);
    }

    @Override
    public <T extends Examinee> void applyResultTo(T person) {
        if(wasSuccess) {
            person.answered().correct();
        } else {
            person.answered().wrong();
        }
    }
}
