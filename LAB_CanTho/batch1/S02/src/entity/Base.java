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
    DEC(10),  // Decimal base
    HEX(16),  // Hexadecimal base
    BIN(2),   // Binary base
    OCT(8);   // Octal base

    private int base;  // Holds the numerical value of the base

    /**
     * Private constructor for enum constants.
     * Initializes the base with a specific integer value representing the base.
     *
     * @param base the numerical value of the base
     */
    private Base(int base) {
        this.base = base;
    }

    /**
     * Returns the enum constant of this type with the specified numeric value.
     * It maps integer values to specific bases.
     *
     * @param type the numeric value of the base
     * @return the Base constant corresponding to the numeric value
     * @throws AssertionError if the numeric value does not correspond to a valid base
     */
    public static Base getBase(int type) {
        switch (type) {
            case 10:
                return DEC;  // Returns Decimal base
            case 16:
                return HEX;  // Returns Hexadecimal base
            case 2:
                return BIN;  // Returns Binary base
            case 8:
                return OCT;  // Returns Octal base
            default:
                throw new AssertionError("Invalid base value: " + type);  // Throws if the base is not recognized
        }
    }

    /**
     * Getter method to obtain the integer value of the base.
     * This value is used for calculations and conversions between bases.
     *
     * @return the integer value of this base
     */
    public int value() {
        return base;
    }
}