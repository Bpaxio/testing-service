package ru.otus.bbpax.service.action;

import org.springframework.stereotype.Component;
import ru.otus.bbpax.model.action.DialogAction;
import ru.otus.bbpax.model.action.EnterNameAction;
import ru.otus.bbpax.service.ConsoleAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vlad Rakhlinskii
 * Created on 11.12.2018.
 */
@Component
public class IntroActionLoader implements ActionLoader {
    private final ConsoleAdapter adapter;

    public IntroActionLoader(ConsoleAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public List<DialogAction> loadActions() {
        List<DialogAction> helloAction = new ArrayList<>();
        helloAction.add(new EnterNameAction(adapter));
        return helloAction;
    }
}
