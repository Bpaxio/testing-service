package ru.otus.bbpax.model.action;

import org.assertj.core.api.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import ru.otus.bbpax.model.result.NameResult;
import ru.otus.bbpax.service.ConsoleAdapter;

import static org.assertj.core.api.AssertionsForClassTypes.anyOf;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("класс EnterNameAction Test")
class EnterNameActionTest {
    private static final String NAME = "NameValue";
    private static final String SURNAME = "SurnameValue";
    private static final String ENTER_NAME_MESSAGE = "intro.name";
    private static final String ENTER_SURNAME_MESSAGE = "intro.surname";
    private static final String INFO_MESSAGE = "intro.info";

    private EnterNameAction action;

    @Mock
    private ConsoleAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = mock(ConsoleAdapter.class);
        doAnswer(this::verifyLocalizedMsg)
                .when(adapter)
                .sendLocalizedMessage(anyString());
        doReturn(NAME, SURNAME)
                .when(adapter).getInput();
        action = spy(new EnterNameAction(adapter));
    }

    private Object verifyLocalizedMsg(InvocationOnMock invocation) {
        String messageCode = invocation.getArgument(0);
        assertThat(messageCode)
                .is(anyOf(new Condition<>(ENTER_NAME_MESSAGE::equals,
                                "equals name message"),
                        new Condition<>(ENTER_SURNAME_MESSAGE::equals,
                                "equals surname message"),
                        new Condition<>(INFO_MESSAGE::equals,
                                "equals info message")
                        )
                );
        return null;
    }

    @Test
    @DisplayName("умеет бегать")
    void runTest() {
        action.run();
        verify(adapter, times(2)).getInput();
        verify(adapter, times(3)).sendLocalizedMessage(anyString());
        verify(adapter, times(0)).sendMessage(anyString());
        verify(action, times(1)).run();
        verify(action, times(0)).getResult();
    }

    @Test
    @DisplayName("умеет давать результат \uD83D\uDE31")
    void getResultTest() {
        NameResult run = action.run();
        assertEquals(run, action.getResult());
        verify(action, times(1)).run();
        verify(action, times(1)).getResult();
    }
}