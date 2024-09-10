/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bo.BaseInputer;
import entity.BaseNumber;
import java.util.Scanner;

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
        while (true) {
            System.out.println("Convert Hexadecimal number/ Octal number to Binary program");
            BaseNumber number = null;
            BaseInputer inputer = new BaseInputer();
            try {
                number = inputer.input();
                BaseNumber numberOfBinary = number.getNumberBinary();
                System.out.println("Number after convert: " + numberOfBinary.getNumber());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("Press any key to do another conversion.");
            Scanner sc = new Scanner(System.in);
            sc.nextLine();
            System.out.println("--------------");
        }
    }

}
