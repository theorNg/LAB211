/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.ArrayHandle;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {

    public static void main(String[] args) {
        ArrayHandle arrayHandle = new ArrayHandle();
        boolean running = true;
        while (running) {
            int option = Validator.getInt("\n1. Add a value\n"
                    + "2. Search a value\n"
                    + "3. Print out the array\n"
                    + "4. Print out values in a range\n"
                    + "5. Sort the array in ascending order\n"
                    + "Other. Quit\n"
                    + "Select option: ", "Must be >0",
                    "Invalid", 1, Integer.MAX_VALUE);

            switch (option) {
                case 1:
                    arrayHandle.addValue();
                    break;
                case 2:
                    arrayHandle.searchValue();
                    break;
                case 3:
                    arrayHandle.printArray();
                    break;
                case 4:
                    arrayHandle.printValuesInRange();
                    break;
                case 5:
                    arrayHandle.sortArray();
                    break;
                default:
                    running = false;
                    break;
            }
        }
    }
}
