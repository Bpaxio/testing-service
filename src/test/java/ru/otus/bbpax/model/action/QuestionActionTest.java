package ru.otus.bbpax.model.action;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import ru.otus.bbpax.model.Question;
import ru.otus.bbpax.model.QuestionImpl;
import ru.otus.bbpax.model.result.QuestionResult;
import ru.otus.bbpax.service.ConsoleAdapter;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class QuestionActionTest {
    private static final String CORRECT_ANSWER = "correct";
    private static final String Q_TITLE = "question?";
    private static final String REPEAT_TITLE = "Please enter one of represented answers";
    private static final String WRONG_ANSWER = "wrong";
    private static final String NOT_ANSWER = "not an answer";

    private Question question;
    private QuestionAction action;
    private ConsoleAdapter adapter;
    private int askingCount;


    @BeforeEach
    void setUp() {
        adapter = mock(ConsoleAdapter.class);
        askingCount = 0;
        doReturn(NOT_ANSWER, WRONG_ANSWER, CORRECT_ANSWER)
                .when(adapter)
                .getInput();
        doAnswer(this::verifyMsg).when(adapter).sendMessage(anyString());
        question = createQuestion();

        action = spy(new QuestionAction(adapter, question));
    }

    private Object verifyMsg(InvocationOnMock invocation) {
        String output = invocation.getArgument(0);
        System.out.println("send msg: " + output);

        switch (askingCount) {
            case 1:
                assertEquals(REPEAT_TITLE, output);
                break;
            default:
                assertEquals(question.getPresentableView(), output);
                break;
        }
        askingCount++;
        return null;
    }

    private Question createQuestion() {
        List<String> answers = new ArrayList<>();
        answers.add(WRONG_ANSWER);
        answers.add(CORRECT_ANSWER);
        answers.add(WRONG_ANSWER);
        answers.add(WRONG_ANSWER);
        return new QuestionImpl(
                Q_TITLE,
                answers,
                1
        );
    }

    @Test
    void run() {
        QuestionResult run = action.run();
        assertFalse(run.wasSuccess());

        run = action.run();
        assertTrue(run.wasSuccess());
        verify(adapter, times(4)).sendMessage(anyString());
        verify(adapter, times(3)).getInput();
    }

    @Test
    void getResult() {
        QuestionResult run = action.run();
        assertEquals(run, action.getResult());
    }
}