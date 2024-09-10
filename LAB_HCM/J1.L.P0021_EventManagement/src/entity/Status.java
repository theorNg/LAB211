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
public enum Status {
    AVAIL("Available"), NOT_AVAIL("Not Available");
    private String status;

    private Status(String status) {
        this.status = status;
    }

    public static Status getStatus(int type) {
        switch (type) {
            case 1:
                return AVAIL;
            case 2:
                return NOT_AVAIL;
            default:
                throw new AssertionError();
        }
    }

    public String getValue() {
        return status;
    }

}
