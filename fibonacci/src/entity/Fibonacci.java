/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author win
 */
public class Fibonacci {
    private List<BigInteger> fibonacci;

    public Fibonacci() {
        fibonacci= new ArrayList<>();
    }
    
    public void displayFibonacci(long number,String message){
        System.out.println(message);
        for (int i = 0; i < fibonacci.size(); i++) {
            System.out.print(fibonacci.get(i));
            if(i==fibonacci.size()-1){
               System.out.print(".");
           }else{
                System.out.print(", ");
            }
        }
    }
    public void generateFibonacci(long number){
        fibonacci.clear();
        generateFibonacciRecursive(number, BigInteger.ZERO, BigInteger.ONE);
    }
    
    private void generateFibonacciRecursive(long number,BigInteger current,BigInteger previous){
        if(number>0){
            fibonacci.add(current);
            BigInteger next = current.add(previous);
            generateFibonacciRecursive(number-1, next, current);
        }
    }
}
