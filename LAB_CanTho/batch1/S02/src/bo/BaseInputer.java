/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Base;
import entity.BaseNumber;
import utils.Validator;

/**
 *
 * @author win
 */
public class BaseInputer {

    private Base base;  // Holds the numerical base of the number (Octal or Hexadecimal)
    private String number;  // Stores the user-entered number string

    /**
     * Inputs a number in hexadecimal or octal format and constructs a BaseNumber object.
     * It prompts the user to input a number string that must end with a specific character ('h' or 'q')
     * to denote hexadecimal or octal base respectively.
     * 
     * @return A new BaseNumber object representing the input number and its base.
     * @throws Exception If the input does not match the expected hexadecimal or octal format.
     */
    public BaseNumber input() throws Exception {
        // Prompt the user to enter a number in hexadecimal ('h' suffix) or octal ('q' suffix) format.
        // Validator.getString() is a hypothetical method that takes:
        // 1. A prompt message to display.
        // 2. An error message to display if the input fails validation.
        // 3. A regular expression that the input must match.
        number = Validator.getString(
            "Enter a Hexadecimal(h)/Octal(q) number: ", 
            "Not a valid number",
            "^([0-7]+q|[0-9A-F]+h)$"
        );

        // Extract the last character of the entered number to determine its base.
        char lastCharacter = number.charAt(number.length() - 1);

        // Determine the base using the last character of the number
        switch (lastCharacter) {
            case 'q':  // If the last character is 'q', set the base to OCT (Octal)
                base = Base.OCT;
                break;
            case 'h':  // If the last character is 'h', set the base to HEX (Hexadecimal)
                base = Base.HEX;
                break;
            default:
                // This block ideally should never be reached due to regex validation.
                // However, including a default case to handle potential unexpected cases is a good practice.
                throw new AssertionError("Invalid last character for base determination: " + lastCharacter);
        }

        // Create a new BaseNumber object with the determined base and the entered number
        // This constructor may throw an exception if the base and number combination is still somehow invalid
        return new BaseNumber(base, number);
    }
}

