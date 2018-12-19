package ru.otus.bbpax.model.action;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import ru.otus.bbpax.service.ConsoleAdapter;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@DisplayName("класс EnterNameAction Test")
class EnterNameActionTest {
    private EnterNameAction action;

    @Mock
    private ConsoleAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = mock(ConsoleAdapter.class);
        action = new EnterNameAction(adapter);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @DisplayName("умеет бегать")
    void runTest() {
        String name = "NameValue";
        String surname = "SurnameValue";

        when(adapter.getInput()).thenReturn(name);

        when(adapter.getInput()).thenReturn(surname);
    }

    @Test
    @DisplayName("умеет давать результат \uD83D\uDE31")
    void getResultTest() {
        System.out.println("m?");
    }
}