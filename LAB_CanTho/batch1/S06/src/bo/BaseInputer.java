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

    public BaseNumber input(int type) throws Exception {
        switch (type) {
            case 1:
                base = Base.BIN;
                number = Validator.getString("Enter binary number: ", "Invalid!", "[01]+");
                break;
            case 2:
                base = Base.OCT;
                number = Validator.getString("Enter octal number: ", "Invalid!", "[0-7]+");
                break;
            case 3:
                base = Base.HEX;
                number = Validator.getString("Enter hexadecimal number: ", "Invalid!", "[0-9A-F]+");
                break;
            default:
                throw new AssertionError();
        }
        return new BaseNumber(base, number);
    }
}
