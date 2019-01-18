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

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public void applyResult(ActionResult result) {
        result.applyResultTo(this);
    }


    public Examinee answered() {
        questionsCount++;
        return this;
    }

    public void correct() {
        correctCount++;
    }

    public void wrong() {
    }

    public Integer getScore() {
        return 100 * correctCount / questionsCount;
    }

    public String getResults() {
        return "Dear, " + name + " " + surname + ".\n" +
                "Your result is: " +
                getScore() + "%";
    }
}
