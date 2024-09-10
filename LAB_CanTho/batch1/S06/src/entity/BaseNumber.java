/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigInteger;

/**
 *
 * @author win
 */
public class BaseNumber {

    private Base base;
    private String number;

    public BaseNumber(Base base, String number) throws Exception {
        if (isValidNumber(base, number)) {
            this.base = base;
            this.number = number;
        } else {
            throw new Exception("Invalid number of base");
        }
    }

    private boolean isValidNumber(Base base, String number) {
        switch (base) {
            case BIN:
                return number.matches("[01]+");
            case OCT:
                return number.matches("[0-7]+");
            case HEX:
                return number.matches("[0-9A-F]+");
            case DEC:
                return number.matches("[0-9]+");
            default:
                throw new AssertionError();
        }
    }

    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public BaseNumber getNumberDecimal() throws Exception {
        BigInteger decNum = BigInteger.ZERO;
        BigInteger base = BigInteger.valueOf(this.base.value());
        BigInteger power = BigInteger.ONE; //Bien tich luy bat dau tu base ^ 0
        for (int i = number.length() - 1; i >= 0; i--) {
            char digit = number.charAt(i);
            BigInteger digitValue = BigInteger.valueOf(Character.getNumericValue(digit));
            decNum = decNum.add(digitValue.multiply(power));
            power = power.multiply(base);
        }
        return new BaseNumber(Base.DEC, decNum.toString());
    }
}
