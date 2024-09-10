/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author win
 */
public class IConstant {

    public static final int CAPTCHA_LENGTH = 5;
    public static final String ACCOUNT_NUMBER = "^[0-9]{10}$";
    public static final String PASSWORD = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,31}$";
    public static final String TEXT = "^[A-Za-z0-9 ,\\.]+$";
    public static final String CAPTCHA = "^[A-Za-z0-9]{5}$";
}
