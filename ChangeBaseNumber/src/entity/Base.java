/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author win
 */
public enum Base {
    DEC(10), HEX(16), BIN(2);
    private int base;

    private Base(int base) {
        this.base = base;
    }

    public static Base getBase(int type) {
        switch (type) {
            case 10:
                return DEC;
            case 16:
                return HEX;
            case 2:
                return BIN;
            default:
                throw new AssertionError();
        }
    }

    public int value() {
        return base;
    }
}
