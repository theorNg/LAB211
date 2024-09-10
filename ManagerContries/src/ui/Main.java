/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.Controller;
import entity.Country;
import entity.EastAsiaCountries;
import utils.Validator;

/**
 *
 * @author win
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Controller control = new Controller();
        while (true) {
            int choice = Validator.getInt("                               MENU\n"
                    + "==========================================================================\n"
                    + "1. Input the information of 11 countries in East Asia\n"
                    + "2. Display the information of country you've just input\n"
                    + "3. Search the information of country by user-entered name\n"
                    + "4. Display the information of countries sorted name in ascending order  \n"
                    + "5. Exit \n"
                    + "==========================================================================\n"
                    + "Enter your choice: ",
                    "Just 1->5",
                    "Invalid!", 1, 5);
            switch (choice) {
                case 1:
                    try {
                        EastAsiaCountries country = control.add();
                        System.out.println("Add success: ");
                        ((Country) country).display();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        control.displayRecentlyEnteredInformation();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    try {
                        control.displayInformationByName();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    try {
                        control.displayInformationByAscendingOrder();
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 5:
                    System.exit(0);
            }
        }
    }

}
