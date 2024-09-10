/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Array;
import entity.BinarySearch;
import entity.BubbleSort;
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
        int number = Validator.getInt("Enter number of array: ", "Please enter number > 0",
                "Please enter integer number", 1, Integer.MAX_VALUE);
        int key = Validator.getInt("Enter search value: ", "Error range!",
                "Please enter integer number", Integer.MIN_VALUE, Integer.MAX_VALUE);
        Array array = new Array();
        int[] randomArray = null;
        try {
            randomArray = array.generateRandomArray(number);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        BubbleSort sort = new BubbleSort(randomArray);
        sort.bubbleSort();
        System.out.println("Sorted array: " + array.toString());
        BinarySearch binary = new BinarySearch(randomArray);
        int index = binary.searchFirstIndex(key);
        if (index == -1) {
            System.out.println("Can not found key!");
        } else {
            System.out.println("Found " + key + " at index: " + index);
        }
    }

}
