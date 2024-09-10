/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bo.BaseInputer;
import entity.BaseNumber;
import java.util.Scanner;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int choice =0;
        while (true) {
            BaseNumber number = null;
            BaseInputer inputer = new BaseInputer();
            choice = Validator.getInt("1. Convert binary number to decimal number\n"
                    + "2. Convert octal number to decimal number\n"
                    + "3. Convert hexadecimal number to decimal number\n"
                    + "4. Exit\n"
                    + "Please choose number (1-4): ", "Just be 1->4", "Invalid!", 1, 4);
            try {
                if(choice==4){
                    break;
                }
                number = inputer.input(choice);
                BaseNumber numberOfBinary = number.getNumberDecimal();
                System.out.println("Decimal number is: " + numberOfBinary.getNumber());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

}
