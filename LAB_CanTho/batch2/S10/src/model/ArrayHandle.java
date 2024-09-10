/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import utils.Validator;

/**
 *
 * @author win
 */
public class ArrayHandle {

    private int[] array;
    private int size;

    public ArrayHandle() {
        array = new int[100];
        size = 0;
    }

    public void addValue() {
        int value = Validator.getInt("Enter an integer to add: ",
                "Error range!", "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (size < 100) {
            array[size++] = value;
        } else {
            System.out.println("Array is full. Cannot add more elements.");
        }
    }


    public void searchValue() {
        int value = Validator.getInt("Enter an integer to search: ",
                "Error range!", "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
            System.out.print("Value found at index: ");
            boolean search =false;
            for (int i = 0; i < size; i++) {
                if (array[i] == value) {
                    search=true;
                    System.out.printf(i+" ");
                }
            }
            if(!search){
                System.out.println("Can not found!");
        }
    }

    public void printArray() {
        if (size == 0) {
            System.out.println("Array is empty!");
            return;
        }
        System.out.print("Array elements: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void printValuesInRange() {
        int min = Validator.getInt("Enter min value: ",
                "Error range!", "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
        int max = Validator.getInt("Enter max value: ",
                "Must be >= min!", "Invalid!", min, Integer.MAX_VALUE);
        System.out.print("Values in range [" + min + ", " + max + "]: ");
        boolean search =false;
        for (int i = 0; i < size; i++) {
            if (array[i] >= min && array[i] <= max) {
                search=true;
                System.out.print(array[i] + " ");
            }
        }
        if(!search){
            System.out.println("Can not found!");
        }
    }

    public void sortArray() {
        // use bubble sort
        for (int i = 0; i < size - 1; i++) {
            for (int j = 0; j < size - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
        System.out.println("Array sorted in ascending order.");
    }
}
