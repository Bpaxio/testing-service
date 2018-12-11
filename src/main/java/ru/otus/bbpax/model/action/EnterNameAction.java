package ru.otus.bbpax.model.action;

import ru.otus.bbpax.model.result.NameResult;
import ru.otus.bbpax.service.ConsoleAdapter;

/**
 * @author Vlad Rakhlinskii
 * Created on 10.12.2018.
 */
public class EnterNameAction implements DialogAction<NameResult> {
    private final ConsoleAdapter adapter;

    private NameResult result;

    public EnterNameAction(ConsoleAdapter adapter) {
        result = new NameResult();
        this.adapter = adapter;
    }

    public NameResult run() {
        adapter.sendMessage("Enter your name.");
        result.setName(adapter.getInput());
        adapter.sendMessage("Enter your surname.");
        result.setSurname(adapter.getInput());
        adapter.sendMessage("Now you are going to pass the test. "
                + "Choose correct answer, put it down and type 'Enter'.");
        return result;
    }

    @Override
    public NameResult getResult() {
        return result;
    }
}
