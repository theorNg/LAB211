/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Scanner;
import model.Dictionary;
import sun.java2d.pipe.ValidatePipe;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {
    public static void main(String[] args) {
        Dictionary dictionary = new Dictionary();

        while (true) {
            System.out.println("1. Create a new word");
            System.out.println("2. Edit a word");
            System.out.println("3. Look up meaning");
            System.out.println("4. Exit");
            int choice = Validator.getInt("Please choose number (1 – 4): ", "Just be 1->4",
                    "Invalid!", 1, 4);
            switch (choice) {
                case 1:
                    dictionary.createWord();
                    break;
                case 2:
                    dictionary.updateWord();
                    break;
                case 3:
                    System.out.println("Meaning: " + dictionary.lookupWord());
                    break;
                case 4:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
