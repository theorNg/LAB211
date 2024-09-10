/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import utils.Validator;

/**
 *
 * @author win
 */
public class ArrayHandler {

    private int[] array;

    public ArrayHandler() {
    }

    public ArrayHandler(int[] array) {
        this.array = array;
    }

    public void input() {
        int number = Validator.getInt("Please enter size of array: ",
                "Just be > 0", "Invalid!", 1, Integer.MAX_VALUE);
        array = new int[number];
        for (int i = 0; i < array.length; i++) {
            array[i] = Validator.getInt("Element[" + i + "] = ", "Error range!",
                    "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }

    public void display() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + "   ");
        }
        System.out.println("");
    }

    public void removeDuplicates() {
        if (array.length == 0 || array.length == 1) {
            return;
        }
        int[] temp = new int[array.length];
        int index = 0;
        for (int i = 0; i < array.length; i++) {
            boolean isDuplicate = false;
            for (int j = 0; j < index; j++) {
                if (array[i] == temp[j]) {
                    isDuplicate = true;
                    break;
                }
            }
            if (!isDuplicate) {
                temp[index++] = array[i];
            }
        }

        array = new int[index];
        for (int i = 0; i < array.length; i++) {
            array[i]=temp[i];
        }
    }
}
