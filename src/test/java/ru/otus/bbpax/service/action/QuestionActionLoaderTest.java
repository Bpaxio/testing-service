package ru.otus.bbpax.service.action;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.bbpax.model.Question;
import ru.otus.bbpax.model.QuestionImpl;
import ru.otus.bbpax.model.action.QuestionAction;
import ru.otus.bbpax.service.ConsoleAdapter;
import ru.otus.bbpax.service.QuestionLoader;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("EN action")
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class QuestionActionLoaderTest {

    @MockBean
    private ConsoleAdapter adapter;

    @Autowired
    private QuestionActionLoader loader;


    @Test
    void loadActions() {
        assertThat(loader.loadActions())
                .isNotNull()
                .isNotEmpty()
                .hasOnlyElementsOfType(QuestionAction.class)
                .hasSize(1);
    }

    @Configuration
    static class Config {
        @Bean
        public QuestionLoader<Question> questionLoader() {
            List<String> answers = new ArrayList<>();
            answers.add("a");
            answers.add("b");
            answers.add("correct");

            return new QuestionLoader<Question>() {
                Question question = new QuestionImpl("Test", answers, 2);
                @Override
                public Question getQuestionById(int id) {
                    return question;
                }

                @Override
                public List<Question> getQuestions() {
                    List<Question> qs = new ArrayList<>();
                    qs.add(question);
                    return qs;
                }
            };
        }

        @Bean
        public QuestionActionLoader loader(ConsoleAdapter adapter,
                                           QuestionLoader<Question> questionLoader) {
            return new QuestionActionLoader(adapter, questionLoader);
        }

    }
}