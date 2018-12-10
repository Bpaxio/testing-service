package ru.otus.bbpax.service;

import java.io.InputStream;
import java.util.Scanner;

/**
 * @author Vlad Rakhlinskii
 * Created on 10.12.2018.
 */
public class ConsoleController {
    private final Scanner scanner;

    public ConsoleController(InputStream inputStream) {
        scanner = new Scanner(inputStream);
    }

    public void startListening() {
        Scanner scanner = new Scanner(System.in);
        String input = null;
        while (!"exit".equals(input)) {
            input = scanner.nextLine();
        }
    }

    public void printMessage(String message) {
        System.out.println(message);
    }


}
