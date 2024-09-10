/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import model.ArrayHandler;

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
        ArrayHandler array = new ArrayHandler();
        array.input();
        System.out.println("The original array:");
        array.display();
        array.removeDuplicates();
        System.out.println("The array after removing duplicate elements:");
        array.display();
    }
    
}
