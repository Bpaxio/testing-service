package ru.otus.bbpax.service.action;

import org.junit.Ignore;
import org.junit.jupiter.api.DisplayName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.bbpax.model.action.EnterNameAction;
import ru.otus.bbpax.service.ConsoleAdapter;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Intro Action Loader")
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class IntroActionLoaderTest {

    @Autowired
    private IntroActionLoader loader;

    @MockBean
    private ConsoleAdapter adapter;

    @Test
    public void loadActions() {
        assertThat(loader.loadActions())
                .isNotNull()
                .isNotEmpty()
                .hasOnlyElementsOfType(EnterNameAction.class)
                .hasSize(1);
    }

    @Configuration
    @Import(IntroActionLoader.class)
    static class Config {

    }
}