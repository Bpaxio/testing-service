package ru.otus.bbpax.model.result;

import ru.otus.bbpax.model.Examinee;

public interface ActionResult {
    String getPresentableView();

    <T extends Examinee> void applyResultTo(T person);
}
