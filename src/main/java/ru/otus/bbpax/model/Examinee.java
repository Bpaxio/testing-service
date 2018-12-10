package ru.otus.bbpax.model;

/**
 * @author Vlad Rakhlinskii
 * Created on 10.12.2018.
 */
public class Examinee {
    private String name;
    private String surname;

    public Examinee setName(String name) {
        this.name = name;
        return this;
    }

    public String getName() {
        return name;
    }

    public Examinee setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getSurname() {
        return surname;
    }
}
