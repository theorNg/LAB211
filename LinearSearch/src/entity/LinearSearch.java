/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author win
 */
public class LinearSearch {
    private int [] array;

    public LinearSearch(int[] array) {
        this.array = array;
    }
    public int searchFirstIndex(int key){
        for (int i = 0; i < array.length; i++) {
            if(array[i]==key){
                return i;
            }
        }
        return -1;
    }
}
