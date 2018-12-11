package ru.otus.bbpax;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.bbpax.model.Question;
import ru.otus.bbpax.model.action.DialogAction;
import ru.otus.bbpax.model.action.EnterNameAction;
import ru.otus.bbpax.model.action.QuestionAction;
import ru.otus.bbpax.model.result.QuestionResult;
import ru.otus.bbpax.service.ConsoleAdapter;
import ru.otus.bbpax.service.QuestionCsvLoader;
import ru.otus.bbpax.service.QuestionLoader;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Vlad Rakhlinskii
 * Created on 06.12.2018.
 */
public class SpringApplication {
    public static ConsoleAdapter controller;

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");
        controller = context.getBean(ConsoleAdapter.class);

        DialogAction helloAction = new EnterNameAction(controller);
        helloAction.run();
        helloAction.getResult();



        QuestionLoader<Question> questionLoader = context.getBean(QuestionCsvLoader.class);
        List<QuestionResult> questionResults = questionLoader.getQuestions().stream()
                .map(SpringApplication::mapToAction)
                .map(action -> {
                    action.run();
                    return action;
                })
                .map(DialogAction::getResult)
                .collect(Collectors.toList());

        controller.sendMessage(helloAction.getResult().getPresentableView() + ", your result:");
        questionResults.forEach(result -> {
            controller.sendMessage(result.getPresentableView());
        });
    }

    private static DialogAction<QuestionResult> mapToAction(Question question) {
        return new QuestionAction(controller, question);
    }
}
