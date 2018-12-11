package ru.otus.bbpax.model.result;

public class NameResult implements ActionResult {
    private String name;
    private String surname;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getPresentableView() {
        return name + "   " + surname;
    }
}
