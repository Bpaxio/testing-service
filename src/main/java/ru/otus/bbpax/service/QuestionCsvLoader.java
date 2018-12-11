package ru.otus.bbpax.service;

import ru.otus.bbpax.model.Question;
import ru.otus.bbpax.model.QuestionImpl;
import ru.otus.bbpax.service.exception.QuestionMissedException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionCsvLoader implements QuestionLoader<Question> {

    private String delimiter;
    private String csvFilePath;

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public void setCsvFilePath(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    @Override
    public Question getQuestionById(int id) {
        return getQuestions().get(id);
    }

    @Override
    public List<Question> getQuestions() {
        try (InputStreamReader reader = new InputStreamReader(
                getClass().getClassLoader().getResourceAsStream(csvFilePath),
                StandardCharsets.UTF_8);

             BufferedReader br = new BufferedReader(reader)) {
            return br.lines()
                    .map(this::parseQuestion)
                    .collect(Collectors.toList());
        } catch (IOException | NullPointerException e) {
            throw new QuestionMissedException(e.toString());
        }
    }

    /**
     * if missed quotes -> fail
     * first is question, second one is index of the correct answers, others are answers.
     * @param line line
     * @return question, defined in line
     */
    private Question parseQuestion(String line) {
        String[] elements = unquotedString(line).split(delimiter);
        String title = elements[0];
        Integer correct = Integer.parseInt(elements[1]);
        return new QuestionImpl(
                title,
                Arrays.asList(elements)
                        .subList(2, elements.length),
                correct);
    }

    public static String unquotedString(String string) {
        return string
                .substring(
                        string.indexOf("\"") == 0 ? 1 : 0,
                        string.lastIndexOf("\"") == string.length() - 1 ? string.length() - 1 : string.length()
                );
    }
}
