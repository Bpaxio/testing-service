package ru.otus.bbpax.service;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * @author Vlad Rakhlinskii
 * Created on 10.12.2018.
 */
public class ConsoleAdapter {
    private final Scanner scanner;
    private final OutputStream out;

    public ConsoleAdapter(InputStream inputStream, OutputStream out) {
        this.scanner = new Scanner(inputStream);
        this.out = out;
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
