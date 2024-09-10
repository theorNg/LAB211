/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import entity.StringProcessor;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String inputer = Validator.getString("Enter a string: ", "Invalid!", "^[A-Za-z ]+(\\.)?$");
        StringProcessor processtor = new StringProcessor(inputer);
        processtor.swapCase();
        processtor.reverseString();
        System.out.println(processtor.getText());
    }

}
