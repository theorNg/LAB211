/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import entity.Fibonacci;

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
        Fibonacci fibonacci = new Fibonacci();
        long maxNumber =200;
        fibonacci.generateFibonacci(maxNumber);
        fibonacci.displayFibonacci(maxNumber, "The "+maxNumber+" sequence fibonacci: ");
    }
    
}