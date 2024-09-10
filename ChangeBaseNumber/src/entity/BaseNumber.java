/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.math.BigInteger;
import utils.Validator;

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
            case DEC:
                return number.matches("[0-9]+");
            case HEX:
                return number.matches("[0-9A-F]+");
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

    private BaseNumber convertToDec() throws Exception {
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

    private BaseNumber convertDecToOut(Base outBase) throws Exception {
        BigInteger decNum = new BigInteger(convertToDec().number);
        StringBuilder reverseResult = new StringBuilder();
        while (decNum.compareTo(BigInteger.ZERO) != 0) {
            int remainNum = decNum.mod(BigInteger.valueOf(outBase.value())).intValue();
            decNum = decNum.divide(BigInteger.valueOf(outBase.value()));
            switch (remainNum) {
                case 10:
                    reverseResult.insert(0, "A");
                    break;
                case 11:
                    reverseResult.insert(0, "B");
                    break;
                case 12:
                    reverseResult.insert(0, "C");
                    break;
                case 13:
                    reverseResult.insert(0, "D");
                    break;
                case 14:
                    reverseResult.insert(0, "E");
                    break;
                case 15:
                    reverseResult.insert(0, "F");
                    break;
                default:
                    reverseResult.insert(0, remainNum);
            }
        }
        if (reverseResult.toString().trim().isEmpty()) {
            reverseResult.insert(0, "0");
        }
        BaseNumber result = new BaseNumber(outBase, reverseResult.toString());
        return result;
    }

    public BaseNumber getOutputByBase(Base outBase) throws Exception {
        return new BaseNumber(outBase, convertDecToOut(outBase).number);
    }
}
