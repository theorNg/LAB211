/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author win
 */
public class Validator {

    private static final Scanner SCANNER = new Scanner(System.in);

    private Validator() {

    }

    public static int getInt(String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                int number = Integer.parseInt(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

    public static double getDouble(String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, double min, double max) {
        do {
            try {
                System.out.print(messageInfo);
                double number = Double.parseDouble(SCANNER.nextLine());
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

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

    public static Date getDate(String messageInfo,
            String messageErrorDate, final String REGEX) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(REGEX);
        dateFormat.setLenient(false);
        do {
            System.out.print(messageInfo);
            try {
                Date date = dateFormat.parse(SCANNER.nextLine());
                return date;
            } catch (ParseException e) {
                System.out.println(messageErrorDate);
            }
        } while (true);
    }

    public static int getIntUpdate(String messageInfo, String messsageErrorOutOfRange,
            String messageErrorNumber, int min, int max) {
        do {
            try {
                System.out.print(messageInfo);
                String string = SCANNER.nextLine();
                if (string.trim().isEmpty()) {
                    return 0;
                }
                int number = Integer.parseInt(string);
                if (number >= min && number <= max) {
                    return number;
                } else {
                    System.out.println(messsageErrorOutOfRange);
                }
            } catch (NumberFormatException e) {
                System.out.println(messageErrorNumber);
            }
        } while (true);
    }

    public static String getStringUpdateRegex(String messageInfo, String messageError, final String REGEX) {
        do {
            System.out.print(messageInfo);
            String str = SCANNER.nextLine();
            if (str.trim().isEmpty()) {
                return str.trim();
            }
            if (str.matches(REGEX)) {
                return str;
            }
            System.out.println(messageError);
        } while (true);
    }

    public static String getStringUpdate(String messageInfo) {
        System.out.print(messageInfo);
        String str = SCANNER.nextLine();
        return str.trim();
    }

    public static Date getDateUpdate(String messageInfo,
            String messageErrorDate, final String REGEX) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(REGEX);
        dateFormat.setLenient(false);
        do {
            System.out.print(messageInfo);
            try {
                String string= SCANNER.nextLine();
                if(string.trim().isEmpty()){
                    return null;
                }
                Date date = dateFormat.parse(string);
                return date;
            } catch (ParseException e) {
                System.out.println(messageErrorDate);
            }
        } while (true);
    }
}
