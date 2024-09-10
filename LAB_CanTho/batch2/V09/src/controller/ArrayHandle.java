/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
        int value = Validator.getInt("Enter value: ",
                "Error range!", "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (size < 100) {
            array[size++] = value;
            System.out.println("Value added successfully.");
        } else {
            System.out.println("Array is full. Cannot add more elements.");
        }
    }

    public void searchValue() {
        int value = Validator.getInt("Enter value: ",
                "Error range!", "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
        System.out.printf("Value found at index: ");
        boolean search = false;
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                search = true;
                System.out.printf(i + " ");
            }
        }
        if (!search) {
            System.out.println("Value not found in the array.");
        }
    }

    public void removeFirstOccurrence() {
        int value = Validator.getInt("Enter value: ",
                "Error range!", "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                for (int j = i; j < size - 1; j++) {
                    array[j] = array[j + 1];
                }
                array[--size] = 0;
                System.out.println("First occurrence of value removed successfully.");
                return;
            }
        }
        System.out.println("Value not found in the array.");
    }

    public void removeAllOccurrences() {
        int value = Validator.getInt("Enter value: ",
                "Error range!", "Invalid!", Integer.MIN_VALUE, Integer.MAX_VALUE);
        int newSize = size;
        for (int i = 0; i < newSize;) {
            if (array[i] == value) {
                for (int j = i; j < newSize - 1; j++) {
                    array[j] = array[j + 1];
                }
                newSize--;
                size--;
            } else {
                i++;
            }
        }
        System.out.println("All occurrences of value removed successfully.");
    }

    public void printArray() {
        System.out.print("Array elements: ");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public void sortArrayAscending() {
        bubbleSort(array, size, true);
        System.out.println("Array sorted in ascending order.");
    }

    public void sortArrayDescending() {
        bubbleSort(array, size, false);
        System.out.println("Array sorted in descending order.");
    }

    private void bubbleSort(int[] arr, int n, boolean ascending) {
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if ((ascending && arr[j] > arr[j + 1])
                        || (!ascending && arr[j] < arr[j + 1])) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

}
