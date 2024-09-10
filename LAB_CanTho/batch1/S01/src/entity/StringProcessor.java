/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author win
 */
public class StringProcessor {

    /** The string to be processed. */
    private String text;

    /**
     * Constructs a StringProcessor object with the specified text.
     * @param text The text to be processed.
     */
    public StringProcessor(String text) {
        this.text = text;
    }

    /**
     * Reverses the string.
     * This method reverses the string by using StringBuilder's reverse method.
     */
    public void reverseString() {
        text = new StringBuilder(this.text).reverse().toString();
    }

    /**
     * Swaps the case of characters in the string.
     * This method iterates through each character in the string,
     * checks if it is uppercase or lowercase, and swaps its case accordingly.
     */
    public void swapCase() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < this.text.length(); i++) { // Loop through each character in the string
            char ch = this.text.charAt(i); // Get the character at index i
            if (Character.isUpperCase(ch)) { // If the character is uppercase
                result.append(Character.toLowerCase(ch)); // Convert it to lowercase and append to result
            } 
            else if (Character.isLowerCase(ch)) { // If the character is lowercase
                result.append(Character.toUpperCase(ch)); // Convert it to uppercase and append to result
            } 
            else { // If the character is neither uppercase nor lowercase
                result.append(ch); // Append the character as is
            }
        }
        text = result.toString(); // Update the text with the modified string
    }

    /**
     * Gets the current text.
     * @return The current text.
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the text to be processed.
     * @param text The text to be processed.
     */
    public void setText(String text) {
        this.text = text;
    }
}
