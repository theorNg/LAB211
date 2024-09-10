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
public enum SalaryStatus {
    UP, DOWN;
    
    public static SalaryStatus getUP() {
        return UP;
    }

    public static SalaryStatus getDOWN() {
        return DOWN;
    }
}
