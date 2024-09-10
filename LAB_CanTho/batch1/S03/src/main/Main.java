/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import convert.NumberToWords;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {

    public static void main(String[] args) {
        boolean continueConversion;
        do {
            int numericInput = Validator.getInt("Please enter string in numeric format: ",
                    "Error range!", "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
            NumberToWords number = new NumberToWords(numericInput);
            System.out.println("The converted string: " + number.getWord());
            String userChoice = Validator.getString("Do you want to continue? (Y/N): ",
                    "Just be Y or N", "[YNyn]");
            continueConversion = userChoice.equalsIgnoreCase("Y");
        } while (continueConversion);
    }
}
