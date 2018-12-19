package ru.otus.bbpax.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * @author Vlad Rakhlinskii
 * Created on 10.12.2018.
 */
@Component
public class ConsoleAdapter {
    private final Scanner scanner;
    private final OutputStream out;
    private final MessageSource messageSource;

    @Autowired
    public ConsoleAdapter(InputStream inputStream, OutputStream out, MessageSource messageSource) {
        this.scanner = new Scanner(inputStream);
        this.out = out;
        this.messageSource = messageSource;
    }

    public String getInput() {
        try {
            return scanner.nextLine();
        } catch (Exception ignored) {
            System.out.println("err");
            return null;
        }
    }

    public void sendLocalizedMessage(String messageCode) {
        try {
            String message = messageSource.getMessage(messageCode, null, Locale.getDefault());
            System.out.println(message);
        } catch (NoSuchMessageException e) {
            System.out.println(messageCode);
        }
    }

    public void sendMessage(String message) {
        System.out.println(message);
    }
}
