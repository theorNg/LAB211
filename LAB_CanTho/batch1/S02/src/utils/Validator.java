/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 *
 * @author win
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Validator() {

    }

    /**
     * Return the valid integer value scanned from the input
     *
     * @param messageInfo the message to be printed instructing the user to
     * input
     * @param messageErrorOutOfRange the message to be printed if the String
     * parse value is out of range
     * @param messageErrorInvalidNumber the message to be printed if the String
     * does not contain a parable integer
     * @param min minimum Limit value
     * @param max maximum Limit value
     * @return the valid integer value scanned from the input
     */
    public static int getInt(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            int min, int max) {
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(scanner.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.out.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                System.out.println(messageErrorInvalidNumber);
            }
        } while (true);
    }

    /**
     * Prompts the user for a string that matches a specified regular
     * expression. If the user input does not match the regex, the error message
     * is displayed, and the user is prompted again.
     *
     * @param messageInfo Information message to display as a prompt to the
     * user.
     * @param messageError Error message to display if the input does not match
     * the regex.
     * @param REGEX The regular expression the input must match.
     * @return A string that matches the given regular expression.
     */
    public static String getString(String messageInfo, String messageError, final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine();
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messageError);
        } while (true);
    }

}
