/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.Base;
import entity.BaseNumber;
import utils.Validator;

/**
 *
 * @author win
 */
public class BaseInputer {

    private Base base;
    private String number;

    public BaseNumber input() throws Exception {
        int choice = Validator.getInt("Enter 1 for binary,2 for decimal,3 for hexadecimal: ",
                "Just 1-> 3", "Invalid!", 1, 3);
        switch (choice) {
            case 1:
                base = Base.BIN;
                number = Validator.getString("Enter a number: ", "Not a valid number", "[01]+");
                break;
            case 2:
                base = Base.DEC;
                number = Validator.getString("Enter a number: ", "Not a valid number", "[0-9]+");
                break;
            case 3:
                base = Base.HEX;
                number = Validator.getString("Enter a number: ", "Not a valid number", "[0-9A-F]+");
                break;
        }
        return new BaseNumber(base, number);
    }
}
