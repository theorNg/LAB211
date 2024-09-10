/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bo.BaseInputer;
import entity.Base;
import entity.BaseNumber;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        // TODO code application logic here
        BaseNumber number = null;
        BaseInputer inputer = new BaseInputer();
        try {
            number = inputer.input();
            int choice = Validator.getInt("Enter 1 for binary,2 for decimal,3 for hexadecimal: ",
                    "Just 1-> 3", "Invalid!", 1, 3);
            BaseNumber result = null;
            switch (choice) {
                case 1:
                    result = number.getOutputByBase(Base.BIN);
                    break;
                case 2:
                    result = number.getOutputByBase(Base.DEC);
                    break;
                case 3:
                    result = number.getOutputByBase(Base.HEX);
                    break;
            }
            System.out.println("Number after convert: " + result.getNumber());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
