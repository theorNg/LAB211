/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ArrayHandle;
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
        ArrayHandle array = new ArrayHandle();
        while (true) {            
            int choice = Validator.getInt("\n**************************************\n"
                    + " 1. Add a value.\n"
                    + " 2. Search a value\n"
                    + " 3. Remove the first existence of a value\n"
                    + " 4. Remove all existences of a value\n"
                    + " 5. Print out the array\n"
                    + " 6. Sort the array in ascending order\n"
                    + " 7. Sort the array in descending order\n"
                    + " Other. Quit\n"
                    + "**************************************\n"
                    + "Enter your choice: ", "Just be >0", "Invalid!", 1, Integer.MAX_VALUE); 
            switch(choice){
                case 1:
                    array.addValue();
                    break;
                case 2:
                    array.searchValue();
                    break;
                case 3:
                    array.removeFirstOccurrence();
                    break;
                case 4:
                    array.removeAllOccurrences();
                    break;
                case 5:
                    array.printArray();
                    break;
                case 6:
                    array.sortArrayAscending();
                    break;
                case 7:
                    array.sortArrayDescending();
                    break;
                default:
                    System.exit(0);
            }
        }
    }
    
}
