package ru.otus.bbpax.service.exception;

/**
 * @author Vlad Rakhlinskii
 * Created on 17.12.2018.
 */
public class QuestionParsingException extends RuntimeException {
    private final static String MESSAGE = "Failed parsing questions. ";
    public QuestionParsingException(String s) {
        super(MESSAGE + s);
    }

    public QuestionParsingException(Throwable cause) {
        super(MESSAGE, cause);
    }
}
