package ru.otus.bbpax.service.exception;

/**
 * @author Vlad Rakhlinskii
 * Created on 11.12.2018.
 */
public class QuestionMissedException extends RuntimeException {
    private final static String MESSAGE = "Questions are missed. ";

    public QuestionMissedException(String message, Throwable cause) {
        super(MESSAGE + message, cause);
    }

    public QuestionMissedException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
