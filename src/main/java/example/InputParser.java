package example;

import java.util.Scanner;

public class InputParser {
    private int answersNumber = 10;

    /**
     *
     * ошибки scanner.next():
     * 098765
     * Matched: 098765
     *
     * 1234 543
     * Matched: 1234
     * Matched: 543
     *
     * ======
     * v2
     * ======
     * 0000000000001
     * чиселко: 1
     * Matched: 0000000000001
     *
     * input examples:
     * 09
     * 000
     * 0
     * 3 a d
     * 1 2
     */
    public void start() {
        Scanner scanner = new Scanner(System.in);
        String input = null;
        while (!"exit".equals(input)) {

//            input = scanner.next();
            input = scanner.nextLine();

            if (input.matches("ответы:[0-9]*")) {
                answersNumber = Integer.parseInt(input.substring("ответы:".length()));
                continue;
            }

            if (isCorrect(input)) {
                System.out.println("Matched: " + input);
            } else {
                System.out.println("Wrong value: " + input);
            }
        }
    }

    public boolean isCorrect(String input) {
        try {
            System.out.println("чиселко: " + Integer.parseInt(input));
            return Integer.parseInt(input) <= answersNumber && Integer.parseInt(input) > 0;
        } catch (NumberFormatException ignored) {
            System.out.println("Not a number!");
            return false;
        }
    }
}
