package ru.otus.bbpax.service.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.otus.bbpax.model.Question;
import ru.otus.bbpax.model.action.DialogAction;
import ru.otus.bbpax.model.action.QuestionAction;
import ru.otus.bbpax.model.result.QuestionResult;
import ru.otus.bbpax.service.ConsoleAdapter;
import ru.otus.bbpax.service.QuestionLoader;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vlad Rakhlinskii
 * Created on 11.12.2018.
 */
@Component
public class QuestionActionLoader implements ActionLoader {
    private final ConsoleAdapter adapter;
    private final QuestionLoader<Question> questionLoader;

    @Autowired
    public QuestionActionLoader(ConsoleAdapter adapter, QuestionLoader<Question> questionLoader) {
        this.adapter = adapter;
        this.questionLoader = questionLoader;
    }

    @Override
    public List<DialogAction> loadActions() {
        return questionLoader.getQuestions().stream()
                .map(this::mapToAction)
                .collect(Collectors.toList());
    }

    private DialogAction<QuestionResult> mapToAction(Question question) {
        return new QuestionAction(adapter, question);
    }
}
