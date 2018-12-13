package ru.otus.bbpax.model.action;

import ru.otus.bbpax.model.result.ActionResult;

public interface DialogAction<R extends ActionResult> {
    R run();

    R getResult();
}
