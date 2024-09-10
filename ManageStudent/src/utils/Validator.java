/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Scanner;

/**
 * @author Tuandz
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Validator() {
    }

     /**
     *Return the valid integer value scanned from the input
     * 
     * @param messageInfo               the message to be printed instructing 
     * the user to input
     * @param messageErrorOutOfRange    the message to be printed if the String 
     * parse value is out of range
     * @param messageErrorInvalidNumber the message to be printed if the String
     * does not contain a parable integer
     * @param min                       minimum Limit value
     * @param max                       maximum Limit value
     * @return the valid integer value scanned from the input
     */
    public static int getInt(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
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
     *Return the valid real number value scanned from the input
     * 
     * @param messageInfo               the message to be printed instructing 
     * the user to input
     * @param messageErrorOutOfRange    the message to be printed if the String 
     * parse value is out of range
     * @param messageErrorInvalidNumber the message to be printed if the String
     * does not contain a parable real number
     * @param min                       minimum Limit value
     * @param max                       maximum Limit value
     * @return the valid real number value scanned from the input
     */
    public static double getDouble(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            double min, double max) {
        do {
            try {
                System.out.println(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine());
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
     *Returns the valid string scanned from the input.
     * 
     * @param messageInfo  the message to be printed instructing
     * the user to input
     * @param messageError the message will be printed if the input 
     * does not match the
     * regular expression
     * @param REGEX        the pattern to test String is valid or not
     * @return the valid string value
     */
    public static String getString(String messageInfo, String messageError,
            final String REGEX) {
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
