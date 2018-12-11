package ru.otus.bbpax.service;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Vlad Rakhlinskii
 * Created on 10.12.2018.
 */
public class ConsoleAdapter {
    private final Scanner scanner;

    public ConsoleAdapter(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public String getInput() {
        try {
            return scanner.nextLine();
        } catch (Exception ignored) {
            System.out.println("err");
            return null;
        }
    }

    public void sendMessage(String message) {
        System.out.println(message);
    }
}
