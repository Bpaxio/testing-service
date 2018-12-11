package ru.otus.bbpax.service;

import ru.otus.bbpax.model.Examinee;
import ru.otus.bbpax.model.action.DialogAction;
import ru.otus.bbpax.model.action.EnterNameAction;
import ru.otus.bbpax.service.action.ActionLoader;

import java.util.List;
import java.util.stream.Collectors;

public class Exam implements ActionRunner {
    private final ConsoleAdapter adapter;

    private List<DialogAction> actions;
    private List<ActionLoader> actionLoaders;

    public Exam(ConsoleAdapter adapter) {
        this.adapter = adapter;
    }

    public void setActionLoaders(List<ActionLoader> actionLoaders) {
        this.actionLoaders = actionLoaders;
    }

    @Override
    public void setUp() {
        actions = actionLoaders.stream()
                .flatMap(actionLoader -> actionLoader.loadActions().stream())
                .collect(Collectors.toList());
    }

    @Override
    public void run() {
        Examinee examinee = new Examinee();
        actions.stream()
                .map(DialogAction::run)
                .forEach(examinee::applyResult);
        adapter.sendMessage(examinee.getResults());
    }
}
