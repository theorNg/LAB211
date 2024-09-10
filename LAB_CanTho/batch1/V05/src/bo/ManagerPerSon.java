/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Person;

/**
 *
 * @author win
 */
public class ManagerPerSon {

    Person[] array;

    public ManagerPerSon() {
        array = new Person[3];
    }

    public void input() {
        for (int i = 0; i < array.length; i++) {
            array[i] = new Person();
            array[i].input();
        }
    }

    public void displayListPerson() {
        for (int i = 0; i < array.length; i++) {
            array[i].display();
        }
    }

    public void sortBySalary() {
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i].getSalary() > array[j].getSalary()) {
                    Person temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }
    }
}
