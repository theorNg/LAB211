/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package array;

import constant.Constant;
import validate.Validator;

/**
 *
 * @author Tuandz
 */
public class Array {

    private int array[] = null;
    private String sort;
    private Constant constant= new Constant();
    public Array() {
    }

    @Override
    public String toString() {
        if (array == null) {
            return null;
        }
        String str = "";
        for (int i = 0; i < array.length; i++) {
            str += "[" + array[i] + "]";
            if (i < array.length - 1 && sort.equals(constant.ASC)) {
                str += "->";
            }
            if (i < array.length - 1 && sort.equals(constant.DESC)) {
                str += "<-";
            }
        }
        return str;
    }

    public void ascending() throws Exception {
        if (array == null) {
            throw new Exception("Error. Please input array!");
        }
        int temp;
        sort = constant.ASC;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public void decending() throws Exception {
        if (array == null) {
            throw new Exception("Error. Please input array!");
        }
        int temp;
        sort = constant.DESC;
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] < array[j + 1]) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    public void input() {
        int number = Validator.getInt("Input Lenght of Array: ",
                "Please input number and number is greater than zero",
                "Please input number and number is greater than zero", 1, Integer.MAX_VALUE);
        array = new int[number];
        for (int i = 0; i < array.length; i++) {
            array[i] = Validator.getInt("Enter number " + (i + 1) + ":", "Error range!",
                    "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
        }
    }
}
