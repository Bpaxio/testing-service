package ru.otus.bbpax.service.exception;

/**
 * @author Vlad Rakhlinskii
 * Created on 11.12.2018.
 */
public class QuestionMissedException extends RuntimeException {
    public QuestionMissedException(String s) {
        super("Questions are missed, because of " + s);
    }
}
