/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import controller.Controller;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        Controller control = new Controller();
        try {
            control.generateStudent();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        while (true) {
            int choice = Validator.getInt("WELCOME TO STUDENT MANAGEMENT\n"
                    + "1.	Create\n"
                    + "2.	Find and Sort\n"
                    + "3.	Update/Delete\n"
                    + "4.	Report\n"
                    + "5.	Exit\n"
                    + "Enter your choice: ", "Just be 1->5", "Invalid!", 1, 5);
            switch (choice) {
                case 1:
                    try {
                        control.createStudent();
                        System.out.println("Add success!");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 2:
                    try {
                        control.findAndSort();
                        System.out.println("Find and sort success!");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 3:
                    try {
                        control.updateOrDelete();
                        System.out.println("Update or Delete success!");
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    try {
                        control.report();
                        System.out.println("Report success!");
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
