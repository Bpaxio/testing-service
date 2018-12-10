package ru.otus.bbpax;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.bbpax.model.Question;
import ru.otus.bbpax.service.QuestionCsvLoader;
import ru.otus.bbpax.service.QuestionLoader;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * @author Vlad Rakhlinskii
 * Created on 06.12.2018.
 */
public class SpringApplication {
    public static void main(String[] args) {
//        if (args.length > 0) {
//            InputParser parser = new InputParser();
//            parser.start();
//        } else {
//            DataLoader loader = new DataLoader();
//            loader.loadFile();
//        }
        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("/spring-context.xml");

        InputStream in = System.in;
        PrintStream out = System.out;

        QuestionLoader<Question> questionLoader = context.getBean(QuestionCsvLoader.class);
        questionLoader.getQuestions().forEach(question -> {
            out.println(question.getPresentableView());
            out.println();
            out.println("===================================");
            out.println();
        });
    }
}
