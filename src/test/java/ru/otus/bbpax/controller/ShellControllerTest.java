package ru.otus.bbpax.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import ru.otus.bbpax.service.Exam;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

@DisplayName("Shell Controller Test")
@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
class ShellControllerTest {
    @Autowired
    private ShellController controller;

    @MockBean
    private Exam exam;

    @Test
    @DirtiesContext
    void hello() {
        String expectedName = "Name";
        String result = controller.hello(expectedName);
        assertEquals("Hi, " + expectedName, result);
    }

    @Test
    void startTest() {
        String testResultMessage = "This is the result message about test";
        doReturn(testResultMessage).when(exam).run();
        assertEquals(testResultMessage, controller.startTest());
    }

    @Test
    void startTestAvailability() {
        assertFalse(controller.startTestAvailability().isAvailable());

        controller.hello("anyName");
        assertTrue(controller.startTestAvailability().isAvailable());
    }

    @Configuration
    @Import(ShellController.class)
    static class Config {

    }
}