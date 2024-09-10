/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bo;

import entity.User;
import utils.Validator;

/**
 *
 * @author win
 */
public class UserInputer {
    
    private User user;
    
    public UserInputer() {
        user = new User();
    }
    
    public User input() {
        user.setUserID(Validator.getString("Enter ID User (Uxxxxx): ",
                "Please enter format Uxxxxx( x is digit)", "[Uu]\\d{5}").toUpperCase());
        user.setName(Validator.getString("Enter user name: ", "Can not empty!", "^(?!\\s*$).+"));
        user.setDateOfBirth(Validator.getDate("Enter date of birth:",
                "Please enter (dd/MM/yyyy)", "dd/MM/yyyy"));
        user.setPhone(Validator.getString("Enter phone: ",
                "Please enter 0xxxxxxxxx(x is digit)!", "^0\\d{9}$"));
        user.setEmail(Validator.getString("Enter email: ", "Invalid!",
                "^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"));
        user.setActive_user(true);
        return user;
    }
}
