/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import array.Array;
import validate.Validator;

/**
 *
 * @author win
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Array array = new Array();
        while (true) {
            int choice = Validator.getInt("===========Bubble Sort program==========\n"
                    + "1. Input Element\n"
                    + "2. Sort Ascending\n"
                    + "3. Sort Decending\n"
                    + "4. Exit\n"
                    + "Please choice one options: ", "Just be 1-> 4", "Invalid!", 1, 4);
            switch (choice) {
                case 1:
                    System.out.println("------Input element-----");
                    array.input();
                    break;
                case 2:
                    System.out.println("------Ascending-----");
                    try {
                        array.ascending();
                        System.out.println(array.toString());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("------Descending-----");
                    try {
                        array.decending();
                        System.out.println(array.toString());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
            }
        }
    }
}
