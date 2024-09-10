/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.util.Arrays;
import utils.Validator;

/**
 *
 * @author win
 */
public class StudentArray {

    private String[] students;
    private int size;

    public StudentArray() {
        students = new String[100];
        size = 0;
    }

    public void addStudent() {
        String name = Validator.getString("Student name to add: ",
                "Can not empty!", "^(?!\\s*$).+");
        if (getIndexStudent(name) != -1) {
            System.out.println("Name is existed!Can not add");
            return;
        }
        if (size < 100) {
            students[size++] = name;
            System.out.println("Student has been added to the list successfully!");
        } else {
            System.out.println("The list is full. It cannot add a new student.");
        }
    }

    public void removeStudent() {
        String name = Validator.getString("Student name to remove: ",
                "Can not empty!", "^(?!\\s*$).+");
        int index = getIndexStudent(name);
        if (index == -1) {
            System.out.println("Student name doesn’t exist in the list.");
        } else {
            for (int i = index; i < size - 1; i++) {
                students[i] = students[i + 1];
            }
            students[--size] = null;
            System.out.println("Student name has been removed from the list successfully!");
        }
    }

    public int getIndexStudent(String name) {
        for (int i = 0; i < size; i++) {
            if (students[i].equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public void searchStudent() {
        String name = Validator.getString("Student name to remove: ",
                "Can not empty!", "^(?!\\s*$).+");
        for (int i = 0; i < size; i++) {
            if (students[i].equals(name)) {
                System.out.println("Student name found at index: " + i);
                return;
            }
        }
        System.out.println("Student name doesn’t exist in the list.");
    }

    public void printStudents() {
        String[] sortedStudents = Arrays.copyOf(students, size);
        bubbleSort(sortedStudents);
        System.out.println("Student names in ascending order:");
        for (String student : sortedStudents) {
            System.out.println(student);
        }
    }

    private void bubbleSort(String[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    String temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }
}
