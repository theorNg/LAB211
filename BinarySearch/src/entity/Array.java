/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Random;

/**
 *
 * @author win
 */
public class Array {
    private int [] array;

    public int[] generateRandomArray(int number) throws Exception{
        if(number<=0){
            throw new Exception("number parameter must be > 0");
        }
        array= new int [number];
        Random random = new Random();
        for (int i = 0; i < array.length; i++) {
            array[i]=random.nextInt(array.length);
        }
        return array;
    }

    @Override
    public String toString() {
        String str="[";
        for (int i = 0; i < array.length; i++) {
            str+=array[i];
            if(i==array.length-1){
                str+="]";
            }else{
                str+=", ";
            }
        }
        return str;
    }
    
    
}
