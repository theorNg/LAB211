/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import entity.Account;
import java.util.ResourceBundle;
import data.Data;
import utils.Helper;
import utils.IConstant;
import utils.Validate;

/**
 *
 * @author win
 */
public class LoginService {
    public void login(ResourceBundle resourceBundle){
        String account = Validate.getString(
                resourceBundle.getString("account"),
                resourceBundle.getString("accountInvalid"),
                IConstant.ACCOUNT_NUMBER );
        
        String password = Validate.getString(
                resourceBundle.getString("password"),
                resourceBundle.getString("passwordInvalid"),
                IConstant.PASSWORD );
        
        String captchaGenerate = Helper.generateCaptcha(IConstant.CAPTCHA_LENGTH);
        System.out.println(resourceBundle.getString("captcha") + captchaGenerate);
        
        Validate.verifyCaptcha(
                resourceBundle.getString("inputCaptcha"), 
                resourceBundle.getString("captchaInvalid"),
                captchaGenerate
        );

        
        if(authentication(account, password)){
            System.out.println(resourceBundle.getString("loginSuccess"));
        } else {
            System.out.println(resourceBundle.getString("loginFailed"));
        }
        
    }
    
    private boolean authentication(String account, String password){
        for(Account a : Data.listAccount){
            if(account.equals(a.getAccount()) && password.equals(a.getPassword())){
                return true;
            }
        }
        return false;
    }
}
