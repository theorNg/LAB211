/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigInteger;

/**
 *
 * @author win
 */
public class BaseNumber {

    private Base base;
    private String number;

    /**
     * Constructs a BaseNumber object with specified base and number.
     * Validates the number according to the base before assigning.
     * Throws an exception if the number does not match the base format.
     * 
     * @param base The base type of the number (Binary, Octal, Decimal, Hexadecimal).
     * @param number The number as a string.
     * @throws Exception If the number is invalid for the specified base.
     */
    public BaseNumber(Base base, String number) throws Exception {
        if (isValidNumber(base, number)) {
            this.base = base;
            this.number = number;
        } else {
            throw new Exception("Invalid number for the specified base");
        }
    }

    /**
     * Validates the number against the specified base using regex patterns.
     * Each base has a specific pattern that the number must match.
     * 
     * @param base The base to validate against.
     * @param number The number as a string.
     * @return true if the number matches the base pattern, false otherwise.
     */
    private boolean isValidNumber(Base base, String number) {
        switch (base) {
            case BIN:
                return number.matches("[01]+b"); // Binary ends with 'b'
            case OCT:
                return number.matches("[0-7]+q"); // Octal ends with 'q'
            case HEX:
                return number.matches("[0-9A-F]+h"); // Hexadecimal ends with 'h'
            case DEC:
                return number.matches("[0-9]+"); // Decimal contains only digits
            default:
                throw new AssertionError("Unsupported base provided");
        }
    }

    /**
     * Returns the formatted number with spaces for better readability.
     * Specifically for binary numbers, groups of four digits are separated by spaces.
     * 
     * @return The formatted number as a string.
     * Loops through the number from end to the beginning to format it for display.
     */
    public String getNumber() {
        int count = 0;
        StringBuilder formattedNumber = new StringBuilder("b");
        // Iterates over the number from second last character to the first
        for (int i = number.length() - 2; i >= 0; i--) {
            count++;
            formattedNumber.insert(0, number.charAt(i));
            // Insert a space every four characters for readability
            if (count == 4) {
                formattedNumber.insert(0, " ");
                count = 0;
            }
        }
        // Trims the resulting string and pads the first group to ensure it has four characters
        formattedNumber = new StringBuilder(formattedNumber.toString().trim());
        while (formattedNumber.toString().split(" ")[0].length() < 4) {
            formattedNumber.insert(0, "0");
        }
        return formattedNumber.toString();
    }

    public void setNumber(String number) {
        this.number = number;
    }

    /**
     * Converts the number from its current base to decimal.
     * The conversion uses the positional notation method by multiplying each digit by its base raised to its position power.
     * 
     * @return A new BaseNumber instance in decimal base.
     * @throws Exception If the base conversion fails.
     */
    private BaseNumber convertToDec() throws Exception {
        BigInteger decNum = BigInteger.ZERO;
        BigInteger baseValue = BigInteger.valueOf(this.base.value());
        BigInteger power = BigInteger.ONE; // Start from base ^ 0
        // Iterate over the number from the second last character to the first
        for (int i = number.length() - 2; i >= 0; i--) {
            char digit = number.charAt(i);
            BigInteger digitValue = BigInteger.valueOf(Character.getNumericValue(digit));
            // Compute the decimal equivalent by adding the digit value multiplied by its positional power
            decNum = decNum.add(digitValue.multiply(power));
            power = power.multiply(baseValue); // Increment the power of base
        }
        return new BaseNumber(Base.DEC, decNum.toString());
    }

    /**
     * Converts a decimal number to binary format by continuously dividing the number by the base (2) and recording the remainder.
     * The remainders represent the binary digits from least significant to most significant.
     * 
     * @return A new BaseNumber instance in binary base.
     * @throws Exception If the base conversion fails.
     */
    private BaseNumber convertDecToBinary() throws Exception {
        BigInteger decNum = new BigInteger(convertToDec().number);
        StringBuilder binaryResult = new StringBuilder();
        // Continuously divide the number by two and prepend the remainder to the result
        while (decNum.compareTo(BigInteger.ZERO) != 0) {
            int remainNum = decNum.mod(BigInteger.valueOf(Base.BIN.value())).intValue();
            decNum = decNum.divide(BigInteger.valueOf(Base.BIN.value()));
            binaryResult.insert(0, remainNum);
        }
        // Ensure the result is not empty; prefix with "0b" if it is
        if (binaryResult.toString().trim().isEmpty()) {
            binaryResult.insert(0, "0b");
        }
        BaseNumber result = new BaseNumber(Base.BIN, binaryResult.toString() + "b");
        return result;
    }

    /**
     * Wrapper method to get the number in binary base, converting it from its current base to binary if necessary.
     * 
     * @return A new BaseNumber instance representing the number in binary base.
     * @throws Exception If conversion to binary fails.
     */
    public BaseNumber getNumberBinary() throws Exception {
        return new BaseNumber(Base.BIN, convertDecToBinary().number);
    }
}

