package ru.otus.bbpax.service.action;

import ru.otus.bbpax.model.action.DialogAction;

import java.util.List;

/**
 * @author Vlad Rakhlinskii
 * Created on 11.12.2018.
 */
public interface ActionLoader {
    List<DialogAction> loadActions();
}
