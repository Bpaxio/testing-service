package ru.otus.bbpax.service;

import ru.otus.bbpax.model.Question;
import ru.otus.bbpax.model.QuestionImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionCsvLoader implements QuestionLoader<Question> {
    private static final String delimiter = "\",\"";

    // TODO: 10.12.2018 fix it!
    private String csvFilePath = getClass()
            .getClassLoader()
            .getResource("fileWithCommaInValues.csv").getPath();

    @Override
    public Question getQuestionById(int id) {
        return getQuestions().get(id);
    }

    @Override
    public List<Question> getQuestions() {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            return br.lines()
                    .map(this::parseQuestion)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * if missed quotes -> fail
     * first is question, second one is index of the correct answers, others are answers.
     * @param line
     * @return
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
