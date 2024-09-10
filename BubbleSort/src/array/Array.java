/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package array;

import constant.Constant;
import java.util.Random;

/**
 *
 * @author Tuandz
 */
public class Array {

    private int array[] = null;

    /**
     * This construct a random array.
     *
     * @param number -length of array
     */
    public Array(int number) {
        Random rand = new Random();
        array = new int[number];
        for (int i = 0; i < number; i++) {
            array[i] = rand.nextInt(number);
        }
    }

    /**
     * This method is used to display Arrays on the screen.
     * @return 
     */
    @Override
    public String toString() {
        String str=" [";
        for (int i = 0; i < array.length; i++) {
            str+=array[i];
            if (i < array.length - 1) {
                str+=", ";
            } else {
                str+="]";
            }
        }
        return str;
    }

    /**
     * This method is used to sort array by bubble sort
     *
     * @param SORT_TYPE
     */
    public void bubbleSort(final String SORT_TYPE) {
        int temp;
        switch (SORT_TYPE) {
            case Constant.ASC:
                for (int i = 0; i < array.length - 1; i++) {
                    for (int j = 0; j < array.length - 1 - i; j++) {
                        if (array[j] > array[j + 1]) {
                            temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                        }
                    }
                }
                break;
            case Constant.DESC:
                for (int i = 0; i < array.length - 1; i++) {
                    for (int j = 0; j < array.length - 1 - i; j++) {
                        if (array[j] < array[j + 1]) {
                            temp = array[j];
                            array[j] = array[j + 1];
                            array[j + 1] = temp;
                        }
                    }
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
}
