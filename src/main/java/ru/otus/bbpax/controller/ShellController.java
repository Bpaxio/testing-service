package ru.otus.bbpax.controller;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;
import ru.otus.bbpax.service.Exam;

@ShellComponent("test")
public class ShellController {
    private final Exam examineService;

    public ShellController(Exam examineService) {
        this.examineService = examineService;
    }

    @ShellMethod("hello")
    public String hello(@ShellOption String name) {
        return "Hi, " + name;
    }

    @ShellMethod("Start passing test")
    public String startTest() {
        examineService.setUp();
        return examineService.run();
    }
}
