/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Random;

/**
 *
 * @author win
 */
public class Helper {

    public static void menu() {
        System.out.println("\n-------Login Program--------");
        System.out.println("1. Vietnamese");
        System.out.println("2. English");
        System.out.println("3. Exit");
    }
    // Ham nay tao ra 1 bang chua chu cai A-Z a-z va chu so 0-9
    private static String genAlphaNumeric() {
        String alpha = "";
        String number = "0123456789";
        String alphaNumeric = "";
        char c = 'A';
        while (c <= 'Z') {
            alpha += c;
            c++;
        }
        alphaNumeric = alpha + alpha.toLowerCase() + number;
        return alphaNumeric;
    }

    public static String generateCaptcha(int length) {
        String alphaNumeric = genAlphaNumeric();
        String captchaGen = "";

        for (int i = 0; i < length; i++) {
            captchaGen += alphaNumeric.charAt(new Random().nextInt(alphaNumeric.length()));
        }

        return captchaGen;
    }
}