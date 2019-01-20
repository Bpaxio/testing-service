package ru.otus.bbpax.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellMethodAvailability;
import org.springframework.shell.standard.ShellOption;
import ru.otus.bbpax.service.Exam;

@ShellComponent("test")
public class ShellController {
    private final Exam examineService;

    private boolean wasGreeted;

    public ShellController(Exam examineService) {
        this.examineService = examineService;
        wasGreeted = false;
    }

    @ShellMethod("hello")
    public String hello(@ShellOption(value = {"", "-n", "--name"}) String name) {
        wasGreeted = true;
        return "Hi, " + name;
    }

    @ShellMethod(value = "Start passing test", key = "test")
    public String startTest() {
        examineService.setUp();
        return examineService.run();
    }

    public Availability startTestAvailability() {
        return wasGreeted
                ? Availability.available()
                : Availability.unavailable("it is unavailable without greetings. "
                + "Call 'hello' first");
    }
}
