package ru.otus.bbpax.model;

import ru.otus.bbpax.model.result.ActionResult;
import ru.otus.bbpax.model.result.NameResult;
import ru.otus.bbpax.model.result.QuestionResult;

/**
 * @author Vlad Rakhlinskii
 * Created on 10.12.2018.
 */
public class Examinee {
    private String name;
    private String surname;
    private int questionsCount;
    private int correctCount;

    public Examinee() {
        this.correctCount = 0;
        this.questionsCount = 0;
    }

    public void applyResult(ActionResult result) {
        if (result instanceof NameResult) {
            applyResult(((NameResult) result));
        } else if (result instanceof QuestionResult) {
            applyResult(((QuestionResult) result));
        }
    }

    private void applyResult(NameResult result) {
        this.name = result.getName();
        this.surname = result.getSurname();
    }

    private void applyResult(QuestionResult result) {
        if(result.wasSuccess()) {
            correctCount++;
        }
        questionsCount++;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public Integer getScore() {
        return 100 * correctCount / questionsCount;
    }

    public String getResults() {
        return "Dear, " + name + " " + surname + ".\n" +
                "Your result is: " +
                100 * correctCount / questionsCount + "%";
    }
}
