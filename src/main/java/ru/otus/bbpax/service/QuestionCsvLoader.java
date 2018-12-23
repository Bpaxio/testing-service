package ru.otus.bbpax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;
import ru.otus.bbpax.model.Question;
import ru.otus.bbpax.model.QuestionImpl;
import ru.otus.bbpax.service.exception.QuestionMissedException;
import ru.otus.bbpax.service.exception.QuestionParsingException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class QuestionCsvLoader implements QuestionLoader<Question> {

    private final MessageSource messageSource;

    private String delimiter;
    private String csvFilePath;

    @Autowired
    public QuestionCsvLoader(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Value("${csv.delimiter:\",\"}")
    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    @Value("${csv.path:file.csv}")
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
        } catch (IOException e) {
            throw new QuestionMissedException("Reading the CSV file was interrupted. ", e);
        } catch (NullPointerException e) {
            throw new QuestionMissedException("CSV File is missing. ", e);
        }
    }

    /**
     * if missed quotes, not parsable file -> fail
     * first is question, second one is index of the correct answers, others are answers.
     * @param line line
     * @return question, defined in line
     */
    private Question parseQuestion(String line) {
        try {
            String[] elements = unquotedString(line).split(delimiter);
            String title = elements[0];
            Integer correct = Integer.parseInt(elements[1]);
            return new QuestionImpl(
                    messageSource.getMessage(title, null, Locale.getDefault()),
                    Arrays.asList(elements)
                            .subList(2, elements.length)
                            .stream()
                            .map(s -> messageSource.getMessage(s, null, Locale.getDefault()))
                            .collect(Collectors.toList()),
                    correct);
        } catch (ArrayIndexOutOfBoundsException | NoSuchMessageException e) {
            throw new QuestionParsingException(e);
        }
    }

    public static String unquotedString(String string) {
        return string
                .substring(
                        string.indexOf("\"") == 0 ? 1 : 0,
                        string.lastIndexOf("\"") == string.length() - 1 ? string.length() - 1 : string.length()
                );
    }
}
