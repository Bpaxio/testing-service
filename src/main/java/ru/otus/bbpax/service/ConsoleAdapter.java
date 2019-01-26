package ru.otus.bbpax.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Locale;
import java.util.Scanner;

/**
 * @author Vlad Rakhlinskii
 * Created on 10.12.2018.
 */
@Component
public class ConsoleAdapter {
    private static final Logger log = LoggerFactory.getLogger(ConsoleAdapter.class);

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
            log.error("shell input can't be scan.", ignored);
            return null;
        }
    }

    public void sendLocalizedMessage(String messageCode) {
        System.out.println(getLocalizedMessage(messageCode));
    }

    public void sendLocalizedMessage(String messageCode, Object... args) {
        System.out.println(getLocalizedMessage(messageCode, args));
    }

    public void sendMessage(String message) {
        System.out.println(message);
    }

    public String getLocalizedMessage(String messageCode) {
        return getLocalizedMessage(messageCode, ((Object) null));
    }

    public String getLocalizedMessage(String messageCode, Object... args) {
        try {
            return messageSource.getMessage(messageCode, args, Locale.getDefault());
        } catch (NoSuchMessageException e) {
            return messageCode;
        }
    }
}
