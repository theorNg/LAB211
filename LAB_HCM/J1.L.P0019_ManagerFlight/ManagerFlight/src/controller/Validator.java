/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author ADMIN
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    public Validator() {
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
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.err.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                System.err.println(messageErrorInvalidNumber);
            }
        } while (true);
    }

    /**
     * Return the valid real number value scanned from the input
     *
     * @param messageInfo the message to be printed instructing the user to
     * input
     * @param messageErrorOutOfRange the message to be printed if the String
     * parse value is out of range
     * @param messageErrorInvalidNumber the message to be printed if the String
     * does not contain a parable real number
     * @param min minimum Limit value
     * @param max maximum Limit value
     * @return the valid real number value scanned from the input
     */
    public static double getDouble(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidNumber,
            double min, double max) {
        do {
            try {
                System.out.print(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                }
                System.err.println(messageErrorOutOfRange);
            } catch (NumberFormatException e) {
                System.err.println(messageErrorInvalidNumber);
            }
        } while (true);
    }

    /**
     * Returns the valid string scanned from the input.
     *
     * @param messageInfo the message to be printed instructing the user to
     * input
     * @param messageError the message will be printed if the input does not
     * match the regular expression
     * @param REGEX the pattern to test String is valid or not
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
            System.err.println(messageError);
        } while (true);
    }

    /**
     * Returns the valid Date scanned from the input
     *
     * @param messageInfo the message to be printed instructing the user to
     * input
     * @param messageErrorOutOfRange the message to be printed if the String
     * parse value is out of range
     * @param messageErrorInvalidDate the message to be printed if the String
     * does not contain a parable date
     * @param REGEX the pattern to test date is valid or not
     * @param min minimum Limit value
     * @param max maximum Limit value
     * @return the valid Date scanned from the input or null if enter blank
     */
    public static Date getDate(
            String messageInfo,
            String messageErrorOutOfRange,
            String messageErrorInvalidDate,
            final String REGEX,
            Date min, Date max) {
        //set format of date
        SimpleDateFormat dateFormat = new SimpleDateFormat(REGEX);
        dateFormat.setLenient(false);
        //force user input exectly a date
        while (true) {
            System.out.print(messageInfo);
            try {
                String dateString = SCANNER.nextLine().trim();
                if (dateString.isEmpty()) {
                    return null;
                }
                Date date = dateFormat.parse(dateString);
                //check range of date
                if (date.compareTo(min) >= 0 && date.compareTo(max) <= 0) {
                    return date;
                }
                System.err.println(messageErrorOutOfRange);
            } catch (ParseException e) {
                System.err.println(messageErrorInvalidDate);
            }
        }
    }
}
