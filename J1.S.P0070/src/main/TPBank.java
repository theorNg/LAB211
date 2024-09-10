/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.Locale;
import java.util.ResourceBundle;
import service.LoginService;
import utils.Helper;
import utils.Validate;

/**
 *
 * @author win
 */
public class TPBank {
    public static void start() {
        Helper.menu();
        int choice = Validate.getInt("Please enter 1-3: ","Just 1->3","Invalid!", 1, 3);
        
        switch(choice){
            case 1:
                Locale.setDefault(new Locale("vi", "VN"));
                break;
            case 2:
                Locale.setDefault(new Locale("en", "US"));
                break;
            case 3:
                System.exit(0);
                break;
        }
        
        ResourceBundle resourceBundle = ResourceBundle.getBundle("resources/Language");
        new LoginService().login(resourceBundle);
    }
}
