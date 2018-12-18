package ru.otus.bbpax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * @author Vlad Rakhlinskii
 * Created on 10.12.2018.
 */
@Component
public class ConsoleAdapter {
    private final Scanner scanner;
    private final OutputStream out;

    @Autowired
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
